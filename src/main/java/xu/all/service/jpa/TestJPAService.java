package xu.all.service.jpa;

import org.springframework.data.domain.Pageable;
import xu.all.entity.jpa.TestJPA;

import java.util.List;

public interface TestJPAService {

    TestJPA getOne(Long id);

    void batchCreate(List<TestJPA> list);

    List<TestJPA> findAll(Pageable pageable);
}
