package xu.all.service.impl;

import xu.all.entity.Test;
import xu.all.repository.TestRepository;
import xu.all.service.TestService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public Test getOne(Long id) {
        Test test = testRepository.findById(id).get();
        DateTime time = new DateTime();
        return test;
    }
}
