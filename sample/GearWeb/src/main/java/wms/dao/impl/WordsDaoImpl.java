package wms.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import com.github.andyshao.reflect.annotation.Param;
import com.github.andyshaox.jdbc.JdbcProcessException;
import com.github.andyshaox.jdbc.SqlAssembly;
import com.github.andyshaox.jdbc.annotation.Dao;
import com.github.andyshaox.jdbc.annotation.Sql;

import wms.dao.WordsDao;
import wms.domain.Words;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 1, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@Dao(dataBase = "Mariadb" , domain = Words.class)
public interface WordsDaoImpl extends WordsDao {
    static class MySqlAssembly implements SqlAssembly {
        private static Properties properties;

        static {
            MySqlAssembly.properties = new Properties();
            try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wms/dao/impl/WordsDaoImpl.properties")) {
                MySqlAssembly.properties.load(inputStream);
            } catch (IOException e) {
                throw new JdbcProcessException(e);
            }
        }

        @Override
        public String assemble(com.github.andyshaox.jdbc.Dao dao , Method method , Object... args) {
            com.github.andyshaox.jdbc.Sql sql = dao.getSqls().get(method);
            return MySqlAssembly.properties.getProperty(sql.getSql());
        }

    }

    //    @Override
    //    @Sql(
    //        value = "INSERT INTO words(id,word_name,insert_time) VALUES('{words.id}','{words.wordName}','{words.insertTime}')" ,
    //        sqlType = SqlType.UPDATE)
    //    public void add(Words words);

    @Override
    @Sql("SELECT id, word_name wordName, insert_time insertTime FROM words WHERE word_name='{wordName}'")
    public Words find(@Param("wordName") String wordName);

    @Override
    @Sql(value = "findTimeLessThan" , sqlAssembly = MySqlAssembly.class)
    public List<Words> findTimeLessThan(String nextTime , long size);

    //    @Override
    //    @Sql("DELETE FROM words WHERE id = '{words.id}'")
    //    public void remove(Words words);
}
