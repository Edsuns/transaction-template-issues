package issues.transactiontemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.function.SingletonSupplier;

/**
 * Created by Edsuns@qq.com on 2022/2/24.
 */
@Service
public final class Transactions {

    static PlatformTransactionManager transactionManager;

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Transactions.transactionManager = transactionManager;
    }

    static final SingletonSupplier<TransactionTemplate> NEW = SingletonSupplier.of(() ->
            createTransactionTemplate(TransactionDefinition.PROPAGATION_REQUIRES_NEW));

    static final SingletonSupplier<TransactionTemplate> REQUIRED = SingletonSupplier.of(() ->
            createTransactionTemplate(TransactionDefinition.PROPAGATION_REQUIRED));

    private static TransactionTemplate createTransactionTemplate(int propagationBehavior) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagationBehavior);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        return transactionTemplate;
    }

    public static TransactionTemplate getNew() {
        return NEW.obtain();
    }

    public static TransactionTemplate getRequired() {
        return REQUIRED.obtain();
    }
}
