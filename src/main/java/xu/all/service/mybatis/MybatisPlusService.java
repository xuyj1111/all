package xu.all.service.mybatis;

import com.baomidou.mybatisplus.extension.service.IService;
import xu.all.entity.mybatis.MybatisPlus;

/**
 * @Description: 为了直接在 controller 层操作，所以这里继承了`IService`
 * 实际开发中不允许这种操作，在`MybatisPlusServiceImpl`中继承了`ServiceImpl`
 * 而`ServiceImpl`已经是`IService`的实现类
 * @Author: xuyujun
 * @Date: 2023/2/14
 */
public interface MybatisPlusService extends IService<MybatisPlus> {

    MybatisPlus getByName(String name);

}
