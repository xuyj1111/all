package xu.all.frw.orika.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private List<String> nameParts;
    private Map<String, String> namePartsMap;
    private Car car;

    public UserDTO(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserDTO(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public UserDTO(Integer id, String name, Integer age, String address, Car car) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.car = car;
    }

    public UserDTO(Integer id, String name, Integer age, List<String> nameParts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nameParts = nameParts;
    }

    public UserDTO(Integer id, String name, Integer age, Map<String, String> namePartsMap) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.namePartsMap = namePartsMap;
    }
}
