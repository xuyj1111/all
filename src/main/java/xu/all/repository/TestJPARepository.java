package xu.all.repository;

import xu.all.entity.jpa.TestJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestJPARepository extends JpaRepository<TestJPA, Long> {

}