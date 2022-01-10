package xu.all.frw.restAssured;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import xu.all.dto.PeopleDTO;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/**
 * @Description: 参考API：https://github.com/xuyj1111/demo/tree/master/src/main/resources/doc/rest-assured用户手册中文版.md
 * @Author: xuyujun
 * @Date: 2021/7/30
 */
public class RestAssuredDemo {

    /**
     * @Description: 查询参数
     * contentType：编码类型
     * port：端口号
     * queryParam：查询参数
     * post：post请求
     * statusCode：校验请求的http code
     * extract+response：得到返回的Response对象
     */
    @Test
    public void queryRest() {
        Response response = given().contentType("application/json").port(8090)
                .queryParam("key", "k1")
                .queryParam("value", "v1")
                .when()
                .post("/rest_assured/query_param")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();
        System.out.println(response.asString());
    }

    /**
     * @Description: 路径参数
     */
    @Test
    public void pathRest() {
        Response response = given().contentType("application/json").port(8090)
                .pathParam("id", 111)
                .when()
                .post("/rest_assured/path_param/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();
        System.out.println(response.asString());
    }

    /**
     * @Description: body传入json
     */
    @Test
    public void bodyRest() {
        Response response = given().contentType("application/json").port(8090)
                .body("{\n" +
                        "    \"name\":\"张三\",\n" +
                        "    \"age\":20,\n" +
                        "    \"car\":{\n" +
                        "        \"brand\":\"奥迪\",\n" +
                        "        \"model\":\"TT\",\n" +
                        "        \"price\":453800\n" +
                        "    }\n" +
                        "}")
                .when()
                .post("/rest_assured/body")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PeopleDTO peopleDTO = objectMapper.readValue(response.asString(), PeopleDTO.class);
            System.out.println(peopleDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}