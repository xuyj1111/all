package xu.all.dto;

import lombok.Data;
import xu.all.interfaces.FirstGroupInterface;
import xu.all.interfaces.SecordGroupInterface;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
* @Description: 测试dto，不对应test表
* @Author: xuyujun
* @Date: 2022/3/1
*/
@Data
public class TestDTO extends BaseDTO{
    private static final long serialVersionUID = -6939309990575010605L;

    @Min(10)
    private Integer number;

    //在First分组时，判断不能为空
    @NotEmpty(groups={FirstGroupInterface.class})
    private String id;

    //在Secord分组时，判断不能为空
    @NotEmpty(groups={SecordGroupInterface.class})
    private String name;
}
