package com.github.andyshaox.spring.jdbc;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.JdbcReturnConvert;
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
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object invoke(Dao dao , Method processMethod , String executableSql , Object... args) {
        Object result = null;
        Sql sql = dao.getSqls().get(processMethod);
        switch (sql.getSqlType()) {
        case UPDATE:
            this.jdbcTemplate.update(executableSql);
            break;
        case EXECUTION:
            this.jdbcTemplate.execute(executableSql);
            break;
        case QUERY:
            result = this.jdbcTemplate.query(executableSql , new ResultSetExtractor<Object>() {
                @Override
                public Object extractData(ResultSet rs) throws SQLException , DataAccessException {
                    if (rs.next()) {
                        @SuppressWarnings("rawtypes")
                        final Class<? extends JdbcReturnConvert> retConvertor = sql.getRetConvertor();
                        if (!retConvertor.equals(JdbcReturnConvert.class)) {
                            JdbcReturnConvert<?> jrc = ClassOperation.newInstance(retConvertor);
                            return jrc.convert(rs);
                        } else return JdbcReturnConvert.genericReturnConvert(dao , processMethod , rs);
                    } else return null;
                }
            });
            break;
        }
        return result;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
