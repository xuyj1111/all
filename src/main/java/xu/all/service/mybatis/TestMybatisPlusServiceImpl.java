package xu.all.service.mybatis;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xu.all.entity.mybatis.TestMybatisPlus;
import xu.all.mapper.TestMybatisPlusMapper;

@Service
public class TestMybatisPlusServiceImpl extends ServiceImpl<TestMybatisPlusMapper, TestMybatisPlus> implements TestMybatisPlusService {

}
