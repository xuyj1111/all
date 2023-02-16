package xu.all.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestDTO extends BaseDTO {
    private static final long serialVersionUID = 7099487348591915155L;
    private String name;
    @JsonProperty("Age")
    private Integer age;
    private Car car;

    @Data
    public static class Car {
        private String brand;
        private String model;
        private Float price;
    }
}
