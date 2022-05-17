package xu.all.service.jpa;

import org.springframework.data.domain.Pageable;
import xu.all.entity.jpa.Jpa;

import java.util.List;

public interface JpaService {

    Jpa getOne(Long id);

    void batchCreate(List<Jpa> list);

    List<Jpa> findAll(Pageable pageable);
}
