package xu.all.service.jpa;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xu.all.entity.jpa.Jpa;
import xu.all.repository.JpaRepository;

import java.util.List;

@Service
public class JpaServiceImpl implements JpaService {

    @Autowired
    private JpaRepository JPARepository;

    @Override
    public Jpa getOne(Long id) {
        Jpa testJpa = JPARepository.findById(id).get();
        return testJpa;
    }

    @Override
    public void batchCreate(List<Jpa> list) {
        list.stream().forEach(a -> {
            a.setDateCreated(DateTime.now());
            a.setLastUpdated(DateTime.now());
        });
        JPARepository.saveAll(list);
    }

    @Override
    public List<Jpa> findAll(Pageable pageable) {
        Page<Jpa> page = JPARepository.findAll(pageable);
        return page.getContent();
    }
}
