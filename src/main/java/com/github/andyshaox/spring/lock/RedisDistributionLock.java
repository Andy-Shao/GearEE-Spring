package com.github.andyshaox.spring.lock;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import com.github.andyshao.lock.DistributionLock;
import com.github.andyshao.lock.ExpireMode;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) 18 Apr 2017<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class RedisDistributionLock implements DistributionLock {
    public static final String DEFAULT_KEY = RedisDistributionLock.class.getName() + "_DISTRIBUTION_LOCK_KEY";
    private final RedisConnectionFactory connFactory;
    private final byte[] lockKey;
    private final int sleepTime;
    private final TimeUnit sleepTimeUnit;
    private final RedisDistributionLock.LockOwer lockOwer = this.new LockOwer();
    
    private class LockOwer {
        private volatile Thread thread;
        private volatile AtomicLong size;
        
        public synchronized boolean increment() {
            if(thread == null) {
                this.thread = Thread.currentThread();
                this.size = new AtomicLong(0L);
                return false;
            } else {
                this.size.incrementAndGet();
                return true;
            }
        }
        
        public synchronized boolean decrement() {
            if(Objects.equals(Thread.currentThread() , this.thread)) {
                if(this.size.longValue() <= 1L) {
                    this.thread = null;
                    this.size = null;
                    return false;
                } else {
                    this.size.decrementAndGet();
                    return true;
                }
            } else return true;
        }
    }
    
    public RedisDistributionLock(RedisConnectionFactory connFactory) {
        this(connFactory , RedisDistributionLock.DEFAULT_KEY);
    }

    public RedisDistributionLock(RedisConnectionFactory connFactory , byte[] lockKey) {
        this(connFactory , lockKey , TimeUnit.NANOSECONDS , 100);
    }

    public RedisDistributionLock(RedisConnectionFactory connFactory , byte[] lockKey , TimeUnit sleepTimeUnit , int sleepTime) {
        this.lockKey = lockKey;
        this.connFactory = connFactory;
        this.sleepTimeUnit = sleepTimeUnit;
        this.sleepTime = sleepTime;
    }

    public RedisDistributionLock(RedisConnectionFactory connFactory , String lockKey) {
        this(connFactory , lockKey.getBytes());
    }

    public RedisDistributionLock(RedisConnectionFactory connFactory , String lockKey , TimeUnit sleepTimeUnit , int sleepTime) {
        this(connFactory , lockKey.getBytes() , sleepTimeUnit , sleepTime);
    }

    private void addExpireTime(RedisConnection conn , ExpireMode expireMode , int expireTimes) {
        if(expireTimes <= 0) return;
        //设置锁的使用超时时间
        switch (expireMode) {
        case SECONDS:
            conn.expire(this.lockKey , expireTimes);
            break;
        case MILISECONDS:
            conn.pExpire(this.lockKey , expireTimes);
            break;
        default:
            break;
        }
    }

    @Override
    public void lock() {
        this.lock(ExpireMode.IGNORE , 1000);
    }

    @Override
    public void lock(ExpireMode expireMode , int expireTimes) {
        RedisConnection conn = null;
        try {
            conn = this.connFactory.getConnection();
            while (!this.tryAcquireLock(conn))
                try {
                    this.sleep();
                } catch (InterruptedException e) {
                }
            this.addExpireTime(conn , expireMode , expireTimes);
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lockInterruptibly(ExpireMode.IGNORE , -1);
    }

    @Override
    public void lockInterruptibly(ExpireMode expireMode , int expireTimes) throws InterruptedException {
        RedisConnection conn = null;
        try {
            conn = this.connFactory.getConnection();
            boolean isGet = false;
            //间隔一段时间获取锁，直到获取
            while (!isGet) {
                if (Thread.currentThread().isInterrupted()) throw new InterruptedException();
                isGet = this.tryAcquireLock(conn);
                this.sleep();
            }
            this.addExpireTime(conn , expireMode , expireTimes);
        } finally {
            if (conn != null) conn.close();
        }
    }

    private void sleep() throws InterruptedException {
        this.sleepTimeUnit.sleep(this.sleepTime);
    }

    private boolean tryAcquireLock(RedisConnection conn) {
        Boolean result = conn.setNX(this.lockKey , this.lockKey);
        if(result) this.lockOwer.increment();
        return result;
    }

    @Override
    public boolean tryLock() {
        return this.tryLock(ExpireMode.IGNORE , -1);
    }

    @Override
    public boolean tryLock(ExpireMode expireMode , int expireTimes) {
        RedisConnection conn = null;
        try {
            conn = this.connFactory.getConnection();
            boolean result = this.tryAcquireLock(conn);
            this.addExpireTime(conn , expireMode , expireTimes);
            return result;
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public void unlock() {
        if(!this.lockOwer.decrement()) {
            RedisConnection conn = null;
            try {
                conn = this.connFactory.getConnection();
                conn.del(this.lockKey);
            } finally {
                if (conn != null) conn.close();
            }
        }
    }
}
