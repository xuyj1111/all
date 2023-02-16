package xu.all.spring.batch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.batch.item.file.LineMapper;
import xu.all.entity.jpa.Message;

import java.util.Map;

/**
 * @Description: 文本的行映射到Java
 * @Author: xuyujun
 * @Date: 2022/2/21
 */
public class MessageLineMapper implements LineMapper<Message> {

    private static final String ID = "id";
    private static final String CONTENT = "content";
    private static final String DATE_CREATED = "date_created";
    private static final String LAST_UPDATED = "last_updated";

    private final DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private final MappingJsonFactory factory = new MappingJsonFactory();

    @Override
    public Message mapLine(String line, int lineNumber) throws Exception {
        JsonParser parser = factory.createParser(line);
        Map<String, String> map = parser.readValueAs(Map.class);
        Message message = new Message();
        message.setId(Long.parseLong(map.get(ID)));
        message.setContent(map.get(CONTENT));
        message.setDateCreated(pattern.parseDateTime(map.get(DATE_CREATED)));
        message.setLastUpdated(pattern.parseDateTime(map.get(LAST_UPDATED)));
        return message;
    }
}
