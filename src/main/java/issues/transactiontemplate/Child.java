package issues.transactiontemplate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Edsuns@qq.com on 2022/2/25.
 */
@Getter
@Setter
@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name = "";

    @Version
    @Column(nullable = false)
    Integer version;
}
