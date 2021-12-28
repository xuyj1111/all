package xu.all.converter;

import xu.all.utils.DateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: DateTime类的消息转换器
 * @Author: xuyujun
 * @Date: 2021/9/10
 */
public class DateTimeJacksonModule extends SimpleModule {

    private static final long serialVersionUID = 75170858260553517L;

    public DateTimeJacksonModule() {
        //添加系列化
        addSerializer(DateTime.class, new DateTimeSerializer());
        //添加反序列化
        addDeserializer(DateTime.class, new DatetimeDeserializer());
    }

    public static class DateTimeSerializer extends JsonSerializer<DateTime> {
        @Override
        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(value.toString(DateTimeUtil.standardDateTimeFormatter));
        }
    }

    public static class DatetimeDeserializer extends JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            String s = node.asText();
            if ("".equals(s)) {
                return null;
            }
            if (StringUtils.isNumeric(s)) {
                Long timestamp = Long.valueOf(s);
                Long millis = timestamp;
                if (timestamp < 1000000000000L) {
                    millis = TimeUnit.MILLISECONDS.convert(timestamp, TimeUnit.SECONDS);
                }
                return new DateTime(millis);
            }
            return DateTime.parse(s, DateTimeUtil.standardDateTimeFormatter);
        }
    }
}
