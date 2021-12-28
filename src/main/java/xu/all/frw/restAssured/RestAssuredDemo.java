package xu.all.frw.restAssured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * @Description: 参考API：https://github.com/xuyj1111/demo/tree/master/src/main/resources/doc/rest-assured用户手册中文版.md
 * @Author: xuyujun
 * @Date: 2021/7/30
 */
public class RestAssuredDemo {

    /**
     * @Description:
     */
    @Test
    public void restBase() {
        given().port(8090)
                .when().get("/rest/user")
                .then()
                .body("name", equalTo("张三"))
                .body("age", hasItems(20, 21));
    }
}