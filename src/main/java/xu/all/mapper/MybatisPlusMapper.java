package xu.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xu.all.entity.mybatis.MybatisPlus;

@Mapper
public interface MybatisPlusMapper extends BaseMapper<MybatisPlus> {

    MybatisPlus getByName(String name);
}
