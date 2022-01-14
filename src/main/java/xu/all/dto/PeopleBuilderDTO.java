package xu.all.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeopleBuilderDTO extends BaseDTO {
    private static final long serialVersionUID = 7099487348591915155L;
    private String name;
    @JsonProperty("Age")
    private Integer age;
    private PeopleDTO.Car car;

    @Data
    public class Car {
        private String brand;
        private String model;
        private Float price;
    }
}
