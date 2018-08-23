package kz.mekhnin.spring.headhunter.usercontext.repositories;

import kz.mekhnin.spring.headhunter.data.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findAllByCurriculumVitae_Id(Long id);
}
