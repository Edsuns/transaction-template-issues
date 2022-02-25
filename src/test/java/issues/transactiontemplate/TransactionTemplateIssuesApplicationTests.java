package issues.transactiontemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class TransactionTemplateIssuesApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    void test() throws Exception {
        final int threads = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        List<Future<?>> tasks = new LinkedList<>();
        for (int i = 0; i < threads; i++) {
            Future<?> task = executorService.submit(() -> {
                mvc.perform(post("/api/demo"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", is(true)));
                return null;
            });
            tasks.add(task);
        }

        try {
            for (Future<?> task : tasks) {
                task.get();
            }
        } catch (ExecutionException e) {
            if (e.getCause() instanceof Exception) {
                throw (Exception) e.getCause();
            }
            throw e;
        } finally {
            executorService.shutdown();
        }
    }

}
