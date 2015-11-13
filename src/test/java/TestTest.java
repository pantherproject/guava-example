import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * maven项目建立成功(单元测试)
 * Created by panther.dongdong on 2015/11/13.
 */
public class TestTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTest.class.getName());

    @Test
    public void test() {
        LOGGER.info("单元测试成功");
    }
}
