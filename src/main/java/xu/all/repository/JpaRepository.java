package xu.all.repository;

import xu.all.entity.jpa.Jpa;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Jpa, Long> {

}