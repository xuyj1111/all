package xu.all.service.mybatis;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xu.all.entity.mybatis.MybatisPlus;
import xu.all.mapper.MybatisPlusMapper;

@Service
public class MybatisPlusServiceImpl extends ServiceImpl<MybatisPlusMapper, MybatisPlus> implements MybatisPlusService {

    @Autowired
    private MybatisPlusMapper mybatisPlusMapper;

    @Override
    public MybatisPlus getByName(String name) {
        return mybatisPlusMapper.getByName(name);
    }
}
