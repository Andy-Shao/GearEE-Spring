package com.github.andyshaox.spring.jdbc;

import java.lang.reflect.Method;

import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.Sql;
import com.github.andyshaox.jdbc.SqlExecution;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 10, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class SpringJdbcSqlExecution implements SqlExecution {
    @Override
    public Object invoke(Dao dao , Method processMethod , String executableSql , Object... args) {
        Object result = null;
        Sql sql = dao.getSqls().get(processMethod);
        switch(sql.getSqlType()){
        case EXECUTION:
            //TODO
            break;
        case QUERY:
            //TODO
            break;
        }
        return result;
    }
}
