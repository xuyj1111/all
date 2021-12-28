package xu.all.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: test表实体类
 * **
 * @Entity： 标识该类为实体类，使用默认的orm规则，即class名即数据库表中表名，class字段名即表中的字段名
 * @Table： 默认类名对应表名，可修改
 * @Column： 默认属性名对应字段名，可修改
 * callSuper = true：不忽视父类的值，不加默认忽视
 * **
 * @Author: xuyujun
 * @Date: 2021/8/24
 */
@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Test extends BaseEntity {

    private static final long serialVersionUID = 2499955129350706462L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
