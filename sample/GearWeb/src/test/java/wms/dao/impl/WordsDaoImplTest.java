package wms.dao.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    @Autowired
    private DaoDetector daoDetector;

    @Test
    public void findTesting() {
        WordsDao dao = this.daoDetector.finding(WordsDao.class);
        Words words = dao.find("test");
        Assert.assertTrue(words != null);
        Assert.assertThat(words.getWordName() , Matchers.is("test"));
    }

    @Test
    public void findTimeLessThanTesting() {
        WordsDao dao = this.daoDetector.finding(WordsDao.class);
        DateFormat dateFormat = new SimpleDateFormat(WordsDaoImplTest.DATE_FORMAT);
        String date = dateFormat.format(new Date());
        List<Words> words = dao.findTimeLessThan(date , 14);
        Assert.assertTrue(words != null);
        Assert.assertThat(words.size() , Matchers.is(14));

        date = "19700101235959";
        words = dao.findTimeLessThan(date , 14);
        Assert.assertTrue(words == null);
    }

    @Test
    public void getDaoTest() throws IOException {
        WordsDao dao = this.daoDetector.finding(WordsDao.class);
        Assert.assertThat(dao.getClass().getName() , Matchers.is("wms.dao.impl.WordsDaoImplEntity"));
    }
}
