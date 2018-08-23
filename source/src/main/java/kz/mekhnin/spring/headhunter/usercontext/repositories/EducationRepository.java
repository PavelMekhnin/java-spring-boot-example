package kz.mekhnin.spring.headhunter.usercontext.repositories;

import kz.mekhnin.spring.headhunter.data.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllByCurriculumVitae_Id(Long id);
}
