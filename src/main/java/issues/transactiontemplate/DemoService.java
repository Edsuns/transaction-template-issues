package issues.transactiontemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by Edsuns@qq.com on 2022/2/25.
 */
@RequiredArgsConstructor
@Service
public class DemoService {

    final TransactionTemplate transactionTemplate;

    final ParentRepository parentRepository;
    final ChildRepository childRepository;

    @PostConstruct
    void init() {
        if (childRepository.count() <= 0) {
            Child child = new Child();
            child.setName("child-1");
            childRepository.save(child);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Retryable(ObjectOptimisticLockingFailureException.class)
    public boolean demo() {
        Child child = childRepository.findFirstByOrderById().orElseThrow();
        // if using `Transactions.getNew()`, the new transaction will not be actually created, which is not expected
        // check the mysql general log, we can see the `insert` and `update` scripts are executed in the same transaction
        // weirdly, changing `Transactions.getNew()` to `transactionTemplate` can fix it
        Transactions.getNew().execute(status -> {
            Assert.state(status.isNewTransaction(), "New transaction required");
            Parent parent = new Parent();
            parent.setChild(child);
            // inserting a parent record will lock(S lock) the child row
            // to avoid deadlock, we start a new transaction to insert it
            return parentRepository.save(parent);
        });
        // updating a record will lock(X lock) the row
        child.setName("child-a");
        childRepository.save(child);
        return true;
    }
}
