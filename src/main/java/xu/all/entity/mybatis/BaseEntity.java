package xu.all.entity.mybatis;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.joda.time.DateTime;
import xu.all.converter.JodaTimeHandler;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    private Long version;

    @TableField(value = "date_created", typeHandler = JodaTimeHandler.class, fill = FieldFill.INSERT)
    private DateTime dateCreated;

    @TableField(value = "last_updated", typeHandler = JodaTimeHandler.class, fill = FieldFill.INSERT_UPDATE)
    private DateTime lastUpdated;
}
