package issues.transactiontemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Edsuns@qq.com on 2022/2/25.
 */
@AutoConfigureMockMvc
@SpringBootTest
public class TestOne {

    @Autowired
    MockMvc mvc;

    @Test
    public void test() {
        assertNotNull(mvc);
    }
}
