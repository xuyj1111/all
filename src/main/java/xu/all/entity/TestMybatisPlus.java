package xu.all.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.joda.time.DateTime;
import xu.all.converter.JodaTimeHandler;

@TableName(value = "test_mybatis_plus", autoResultMap = true)
@Data
public class TestMybatisPlus {
    private static final long serialVersionUID = 683669611339546726L;

    @TableId
    private Long id;

    private String name;

    @TableField(value = "date_created", typeHandler = JodaTimeHandler.class, fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_EMPTY)
    private DateTime dateCreated;

    @TableField(value = "last_updated", typeHandler = JodaTimeHandler.class, fill = FieldFill.INSERT_UPDATE)
    private DateTime lastUpdated;
}
