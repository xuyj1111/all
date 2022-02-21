package xu.all.spring.batch.messageJob;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;
import xu.all.entity.Message;

import java.io.Writer;
import java.util.List;

/**
 * @Description: 监听写操作
 * @Author: xuyujun
 * @Date: 2022/2/21
 */
@Slf4j
public class MessageWriteListener implements ItemWriteListener<Message> {

    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends Message> items) {

    }

    @Override
    public void afterWrite(List<? extends Message> items) {

    }

    @SneakyThrows
    @Override
    public void onWriteError(Exception exception, List<? extends Message> items) {
        log.error(exception.getMessage());
        for (Message message : items) {
            log.error("Failed writing message id: " + message.getId());
        }
    }
}
