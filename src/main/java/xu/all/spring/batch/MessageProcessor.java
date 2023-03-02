package xu.all.spring.batch;

import org.springframework.batch.item.ItemProcessor;
import xu.all.entity.jpa.Message;

public class MessageProcessor implements ItemProcessor<Message, Message> {
    @Override
    public Message process(Message item) {
        System.out.println("item: " + item.toString());
        return item;
    }
}