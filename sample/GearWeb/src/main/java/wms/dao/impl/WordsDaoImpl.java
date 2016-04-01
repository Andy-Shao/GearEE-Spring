package wms.dao.impl;

import com.github.andyshaox.jdbc.annotation.Dao;
import com.github.andyshaox.jdbc.annotation.Sql;

import wms.dao.WordsDao;
import wms.domain.Words;

@Dao(dataBase = "Mariadb")
public interface WordsDaoImpl extends WordsDao {

    @Sql("SELECT id, word_name AS 'wordName', insert_time AS 'insertTime' FROM words LIMIT 1")
    Words showFirstLine();
}
