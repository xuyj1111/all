package xu.all.dto;

import lombok.Data;

@Data
public class PeopleDTO extends BaseDTO {
    private static final long serialVersionUID = 7099487348591915155L;
    private String name;
    private Integer age;
    private Car car;

    @Data
    public class Car {
        private String brand;
        private String model;
        private Float price;
    }
}
