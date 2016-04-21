package logging.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:logging/log4j/applicationContext.xml")
public class LoggingTesting {
    private static final Logger LOG = Logger.getLogger(LoggingTesting.class);

    @Test
    public void test() {
        LoggingTesting.LOG.info("MY TESTING");
    }
}
