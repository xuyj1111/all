package xu.all.converter;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName("dateCreated", DateTime.now(), metaObject);
        setInsertFieldValByName("lastUpdated", DateTime.now(), metaObject);
        setInsertFieldValByName("version", 1L, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName("lastUpdated", DateTime.now(), metaObject);
    }
}
