package xu.all.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xu.all.entity.Test;
import xu.all.repository.TestRepository;
import xu.all.service.TestService;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public Test getOne(Long id) {
        Test test = testRepository.findById(id).get();
        return test;
    }

    @Override
    public void batchCreate(List<Test> list) {
        list.stream().forEach(a -> {
            a.setDateCreated(DateTime.now());
            a.setLastUpdated(DateTime.now());
        });
        testRepository.saveAll(list);
    }

    @Override
    public List<Test> findAll(Pageable pageable) {
        Page<Test> page = testRepository.findAll(pageable);
        return page.getContent();
    }
}
