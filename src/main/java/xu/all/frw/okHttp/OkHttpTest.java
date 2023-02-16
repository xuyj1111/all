package xu.all.frw.okHttp;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

/**
 * @Description: 与OkHttpController对应
 * @Author: xuyujun
 * @Date: 2022/1/14
 */
public class OkHttpTest {

    /**
     * @Description:
     */
    @Test
    public void send(){
        Response response = given().contentType("application/json").port(8090)
                .queryParam("isFail", true)
                .when()
                .post("/okHttp/send")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();
    }
}
