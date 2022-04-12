package xu.all.service.jpa;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xu.all.entity.jpa.TestJPA;
import xu.all.repository.TestJPARepository;

import java.util.List;

@Service
public class TestJPAServiceImpl implements TestJPAService {

    @Autowired
    private TestJPARepository testJPARepository;

    @Override
    public TestJPA getOne(Long id) {
        TestJPA testJPA = testJPARepository.findById(id).get();
        return testJPA;
    }

    @Override
    public void batchCreate(List<TestJPA> list) {
        list.stream().forEach(a -> {
            a.setDateCreated(DateTime.now());
            a.setLastUpdated(DateTime.now());
        });
        testJPARepository.saveAll(list);
    }

    @Override
    public List<TestJPA> findAll(Pageable pageable) {
        Page<TestJPA> page = testJPARepository.findAll(pageable);
        return page.getContent();
    }
}
