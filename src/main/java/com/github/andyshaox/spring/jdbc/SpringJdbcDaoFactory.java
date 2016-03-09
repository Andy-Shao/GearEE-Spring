package com.github.andyshaox.spring.jdbc;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import com.github.andyshao.asm.Version;
import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.DaoFactory;
import com.github.andyshaox.jdbc.JdbcProcessException;

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
        if (!dao.getDefineClass().isInterface()) throw new JdbcProcessException("Defining class should be a interface");
        try {
            final Object entity = ClassOperation.newInstanceForInterface(dao.getDefineClass() ,
                dao.getDefineClass() + "Entity" , false , Version.V1_8);
            final Set<Class<?>> set = new HashSet<>();
            ClassOperation.superGetInterfaces(dao.getDefineClass() , set);
            return Proxy.newProxyInstance(dao.getDefineClass().getClassLoader() ,
                set.toArray(new Class<?>[set.size()]) , (target , method , args) -> {
                    if (!dao.getSqls().containsKey(method)) return method.invoke(entity , args);
                    return null;
                });
        } catch (IOException e) {
            throw new JdbcProcessException("Cannot build interface entity" , e);
        }
    }

}
