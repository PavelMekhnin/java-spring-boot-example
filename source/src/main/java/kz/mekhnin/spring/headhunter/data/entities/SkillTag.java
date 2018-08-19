package kz.mekhnin.spring.headhunter.data.entities;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;

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

    @ManyToMany
    @JoinTable(name="cv_skillTag",
            joinColumns=@JoinColumn(name="sillTagId", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="cv_id", referencedColumnName="id"))
    private List<CurriculumVitae> curriculumVitaes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CurriculumVitae> getCurriculumVitaes() {
        return curriculumVitaes;
    }

    public void setCurriculumVitaes(List<CurriculumVitae> curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }
}
