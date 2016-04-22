package wms.dao.impl;

import java.util.List;

import com.github.andyshao.reflect.MethodOperation;
import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.SqlExecution;

import wms.dao.WordsDao;
import wms.domain.Words;

public class WordsDaoDemo implements WordsDao {
    private Dao dao;
    private SqlExecution sqlExecution;

    @Override
    public void add(Words words) {
    }

    @Override
    public Words find(String wordName) {
        return null;
    }

    @Override
    public long findTimeBigNum(String nextTime) {
        return 0;
    }

    @Override
    public long findTimeLesNum(String nextTime) {
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Words> findTimeLessThan(String nextTime , long size) {
        return (List<Words>) this.sqlExecution.invoke(this.dao , MethodOperation.getMethod(this.dao.getDefineClass() , "findTimeLessThan" , new Class<?>[] { String.class , long.class }) , null ,
            new Object[] { nextTime , size });
    }

    @Override
    public void remove(Words words) {
    }

    @Override
    public long totally() {
        return 0;
    }

    @Override
    public void update(Words words) {
    }

    @Override
    public void remove(String wordsId) {
    }

    @Override
    public List<Words> findInfoByLess(String nextTime , long start , long length) {
        return null;
    }
}
