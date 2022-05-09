package xu.all.dto;

import lombok.Data;
import xu.all.interfaces.FirstGroupInterface;
import xu.all.interfaces.SecordGroupInterface;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    /**
     * @Description: @Valid 的嵌套校验
     */
    @Valid
    private InnerDTO innerDTO;

    /**
     * @Description: @Validated 的分组校验
     */
    @NotEmpty(groups={FirstGroupInterface.class})
    private String id;
    @NotEmpty(groups={SecordGroupInterface.class})
    private String name;

    @Data
    public class InnerDTO {
        @NotNull
        private Long id;

        @NotBlank
        private String name;
    }
}
