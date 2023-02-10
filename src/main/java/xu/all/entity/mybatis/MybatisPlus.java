package xu.all.entity.mybatis;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@TableName(value = "mybatis_plus", autoResultMap = true)
@Data
public class MybatisPlus extends BaseEntity{
    private static final long serialVersionUID = 683669611339546726L;

    private String name;
}
