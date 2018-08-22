package kz.mekhnin.spring.headhunter.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "SkillTags")
public class SkillTag {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    //private List<CurriculumVitae> curriculumVitaes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
