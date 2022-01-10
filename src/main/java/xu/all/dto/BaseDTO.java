package xu.all.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @Description: dto基础类
 * @Author: xuyujun
 * @Date: 2022/1/10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTO implements Serializable {

}
