package kz.mekhnin.spring.headhunter.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LanguageSkills")
public class LanguageSkill {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int grade;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Language.class)
    @JoinColumn(name = "languageId",referencedColumnName="id", nullable=false, unique=true)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CurriculumVitae.class)
    @JoinColumn(name = "curriculumVitaeId",referencedColumnName="id", nullable=false, unique=true)
    private CurriculumVitae curriculumVitae;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }
}
