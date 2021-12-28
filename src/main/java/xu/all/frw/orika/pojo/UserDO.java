package xu.all.frw.orika.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

    private Integer id;
    private String userName;
    private Integer userAge;
    private String address;
    private String firstName;
    private String lastName;
    private String carName;
    private String carBrand;

    public UserDO(Integer id, String userName, Integer userAge) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
    }

    public UserDO(Integer id, String userName, Integer userAge, String address) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
        this.address = address;
    }
}
