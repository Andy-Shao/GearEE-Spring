package wms.dao.impl;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.andyshaox.jdbc.DaoDetector;

import wms.dao.WordsDao;
import wms.domain.Words;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:conf/spring/databases/datasources/mariadbArg.xml" , "classpath:conf/spring/databases/datasources/dataSource.xml" , "classpath:conf/spring/dao/mariadbDao.xml" })
public class WordsDaoImplTest {
    @Autowired
    private DaoDetector daoDetector;

    @Test
    public void getDaoTest() throws IOException {
        WordsDao dao = this.daoDetector.finding(WordsDao.class);
        Words words = dao.find("test");
        Assert.assertTrue(words != null);
        Assert.assertThat(words.getWordName() , Matchers.is("test"));
    }
}
