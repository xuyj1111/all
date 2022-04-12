package xu.all.entity.mybatis;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "test_mybatis_plus", autoResultMap = true)
@Data
public class TestMybatisPlus extends BaseEntity{
    private static final long serialVersionUID = 683669611339546726L;

    private String name;
}
