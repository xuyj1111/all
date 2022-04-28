package xu.all.jdk.jdk8.stream;

import lombok.Data;

@Data
public class DemoDTO {
    private String aStr;
    private Integer aInt;
    private Float aFloat;
    private Double aDouble;
    private SonDTO sonDTO;


    @Data
    public class SonDTO {
        private String aStr;
    }
}
