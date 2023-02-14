package xu.all.config;

import xu.all.converter.DateTimeJacksonModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.PostConstruct;
import java.util.Iterator;

/**
 * @Description: 在SpringMVC中，可以使用@RequestBody和@ResponseBody两个注解，分别完成请求报文到对象和对象到响应报文的转换，底层这种灵活的消息转换机制就是利用HttpMessageConverter来实现的;
 * 下面自定义DateTime类的消息转换器
 * @Author: xuyujun
 * @Date: 2021/9/10
 */
@Configuration
public class ObjectMapperConfig {
    @Autowired
    private HttpMessageConverters messageConverters;

    // @PostConstruct 是在被 service 调用之前，为 bean 进行初始化使用；而 @PreDestroy 是在销毁 bean 之前使用
    @PostConstruct
    public void registerJacksonModule() {
        //默认加载多个HttpMessageConverter，获得迭代器
        for (HttpMessageConverter<?> httpMessageConverter : messageConverters.getConverters()) {
            //遍历，找到MappingJackson2HttpMessageConverter
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) httpMessageConverter;
                ObjectMapper mapper = converter.getObjectMapper();
                //属性为NULL，不序列化
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                //映射不到对应的属性，没有任何setter和handler处理，抛出异常，默认开启
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                //注册具体实现转换的模块
                mapper.registerModule(new DateTimeJacksonModule());
                //开启支持解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量），默认false
                mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
                //开启支持浮点型，默认false
                mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
                //开启支持整型，默认false
                mapper.enable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);
                //开启防止科学计数法符号写入，默认false
                mapper.enable(com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            }
        }
    }
}