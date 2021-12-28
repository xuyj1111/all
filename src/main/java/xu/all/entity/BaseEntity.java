package xu.all.entity;

import xu.all.converter.JodaTimeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @Description: 数据库实体基类
 * **
 * @MappedSuperclass： 标识父类，不会映射到数据库表，但子类会映射
 * @EntityListeners(AuditingEntityListener.class)： 监听器，此处用于dateCreated和lastUpdated字段自动赋值
 * **
 * @Author: xuyujun
 * @Date: 2021/8/24
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Convert(converter = JodaTimeConverter.class)
    @CreatedDate
    @Column(updatable = false)
    private DateTime dateCreated;

    @Convert(converter = JodaTimeConverter.class)
    @LastModifiedDate
    private DateTime lastUpdated;
}
