package com.github.andyshaox.spring.jdbc;

import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.DaoFactory;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 30, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class SpringJdbcDaoFactory implements DaoFactory {

    @Override
    public Object getDao(final Dao dao) {
        // TODO Auto-generated method stub
        Set<Class<?>> set = new HashSet<>();
        ClassOperation.superGetInterfaces(dao.getDefineClass() , set);
        return Proxy.newProxyInstance(dao.getDefineClass().getClassLoader() , set.toArray(new Class<?>[set.size()]) ,
            (target , method , args) -> {
                if (!dao.getSqls().contains(method)) return null;
                return null;
            });
    }

}
