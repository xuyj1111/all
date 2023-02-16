package xu.all.frw.jackson;

import org.junit.jupiter.api.Test;
import xu.all.frw.jackson.pojo.JsonDTO;
import xu.tools.json.JsonMapper;

import java.math.BigDecimal;

/**
 * @Description: 该demo使用tools服务简单封装的Jackson
 * @Author: xuyujun
 * @Date: 2022/1/18
 */
public class JacksonTest {

    /**
     * @Description:
     */
    @Test
    public void writeValueAsString() {
        JsonDTO jsonDTO = new JsonDTO();
        jsonDTO.setName("张三");
        jsonDTO.setAge(20);
        jsonDTO.setDecimal(new BigDecimal("0.00000001"));
        jsonDTO.setAFloat(0.001f);
        System.out.println(JsonMapper.writeValueAsString(jsonDTO));
    }
}
