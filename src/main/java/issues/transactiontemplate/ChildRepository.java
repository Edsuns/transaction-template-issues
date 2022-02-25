package issues.transactiontemplate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Edsuns@qq.com on 2022/2/25.
 */
public interface ChildRepository extends JpaRepository<Child, Integer> {

    Optional<Child> findFirstByOrderById();
}
