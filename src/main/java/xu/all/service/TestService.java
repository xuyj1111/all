package xu.all.service;

import org.springframework.data.domain.Pageable;
import xu.all.entity.Test;

import java.util.List;

public interface TestService {

    Test getOne(Long id);

    void batchCreate(List<Test> list);

    List<Test> findAll(Pageable pageable);
}
