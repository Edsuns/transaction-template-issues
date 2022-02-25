package issues.transactiontemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Edsuns@qq.com on 2022/2/25.
 */
@RequiredArgsConstructor
@RestController
public class DemoController {

    final DemoService demoService;

    @PostMapping("/api/demo")
    public boolean demo() {
        return demoService.demo();
    }
}
