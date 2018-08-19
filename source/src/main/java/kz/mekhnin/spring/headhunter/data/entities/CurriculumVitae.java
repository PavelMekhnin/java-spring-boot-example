package kz.mekhnin.spring.headhunter.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "CurriculumVitaes")
public class CurriculumVitae {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private String summary;

    @OneToMany(
            mappedBy = "curriculumVitae",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
            ,targetEntity = Education.class
    )
    private Set<Education> educations;

    @OneToMany(
            mappedBy = "curriculumVitae",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
            ,targetEntity = Experience.class
    )
    private Set<Experience> experiences;

    @ManyToMany(
            mappedBy = "curriculumVitaes",
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER,
            targetEntity = SkillTag.class
    )
    private Set<SkillTag> skillTags;

    @OneToMany(
            mappedBy = "curriculumVitae",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
            ,targetEntity = LanguageSkill.class
    )
    private Set<LanguageSkill> languageSkills;

    @OneToMany(
            mappedBy = "curriculumVitae",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
            ,targetEntity = Award.class
    )
    private Set<Award> awards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName="id", nullable=false, unique=true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public Set<SkillTag> getSkillTags() {
        return skillTags;
    }

    public void setSkillTags(Set<SkillTag> skillTags) {
        this.skillTags = skillTags;
    }

    public Set<LanguageSkill> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(Set<LanguageSkill> languageSkills) {
        this.languageSkills = languageSkills;
    }


    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
