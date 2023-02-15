package xu.all.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @Description: dto基础类
 * @Author: xuyujun
 * @Date: 2022/1/10
 */
/**
 * @Description: 遇到未知属性不报错
 * 若使用 tools-json 中的 JsonMapper，则不需要该注解，因为已经将 tools-json 中的 ObjectMapper 配置"遇到未知属性不报错"
 * 若使用 new ObjectMapper()，则需要该注解
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTO implements Serializable {

}
