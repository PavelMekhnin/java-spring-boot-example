package kz.mekhnin.spring.headhunter.usercontext.repositories;

import kz.mekhnin.spring.headhunter.data.entities.SkillTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillTagRepository extends JpaRepository<SkillTag, Long> {
    List<SkillTag> findAllByTitleLike(String name);
    SkillTag findByTitle(String name);
}
