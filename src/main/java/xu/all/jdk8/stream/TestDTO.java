package xu.all.jdk8.stream;

import lombok.Data;

@Data
public class TestDTO {
    private String aStr;
    private Integer aInt;
    private Float aFloat;
    private Double aDouble;
    private SonDTO sonDTO;


    @Data
    public static class SonDTO {
        private String aStr;
    }
}
