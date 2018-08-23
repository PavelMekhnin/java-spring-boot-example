package kz.mekhnin.spring.headhunter.usercontext.repositories;

import kz.mekhnin.spring.headhunter.data.entities.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
    List<Award> findAllByCurriculumVitae_Id(Long id);
}
