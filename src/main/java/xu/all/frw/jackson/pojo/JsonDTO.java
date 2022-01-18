package xu.all.frw.jackson.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JsonDTO {
    private String name;
    private Integer age;
    private BigDecimal decimal;
    private Float aFloat;
}
