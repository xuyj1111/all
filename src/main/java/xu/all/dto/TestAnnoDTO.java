package xu.all.dto;

import lombok.Data;
import xu.all.annotation.SimpleAnno;

/**
 * @Description: 使用自定义注解
 * @Author: xuyujun
 * @Date: 2023/2/3
 */
@Data
@SimpleAnno(name = "王铁柱", age = 59)
public class TestAnnoDTO {

    private String name;
    private int age;

}
