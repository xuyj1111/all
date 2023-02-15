package xu.all.frw.restAssured;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import xu.all.dto.DemoDTO;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @Description: 参考API：https://github.com/xuyj1111/demo/tree/master/src/main/resources/doc/rest-assured用户手册中文版.md
 * @Author: xuyujun
 * @Date: 2021/7/30
 */
@Slf4j
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
        assertThat(response.asString(), is("k1:v1"));
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
        assertThat(response.asString(), is("id: 111"));
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
            DemoDTO demoDTO = objectMapper.readValue(response.asString(), DemoDTO.class);
            assertThat(demoDTO.getAge(), is(25));
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }
}