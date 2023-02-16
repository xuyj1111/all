package xu.all.spring.batch;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import xu.all.entity.jpa.Message;

/**
 * @Description: 监听读操作
 * @Author: xuyujun
 * @Date: 2022/2/21
 */
@Slf4j
public class MessageItemReadListener implements ItemReadListener<Message> {

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Message item) {

    }

    @SneakyThrows
    @Override
    public void onReadError(Exception ex) {
        log.error(ex.getMessage());
    }
}
