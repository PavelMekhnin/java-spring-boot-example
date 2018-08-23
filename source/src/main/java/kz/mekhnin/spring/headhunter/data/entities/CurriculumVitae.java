package kz.mekhnin.spring.headhunter.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "CurriculumVitaes")
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Long id;

    @NotBlank
    private String title;

    private String summary;

    @OneToMany(
            mappedBy = "curriculumVitae",
            targetEntity = Education.class,
            cascade={CascadeType.REMOVE}
            )
    private List<Education> educations = new ArrayList<>();

    @OneToMany(
            mappedBy = "curriculumVitae",
            targetEntity = Experience.class,
            cascade={CascadeType.REMOVE}
            )
    private List<Experience> experiences= new ArrayList<>();

    //private Set<SkillTag> skillTags;

    //private Set<LanguageSkill> languageSkills;

    @OneToMany(
            mappedBy = "curriculumVitae",
            targetEntity = Award.class,
            cascade={CascadeType.REMOVE}
            )
    private List<Award> awards= new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    public CurriculumVitae(){}

    public CurriculumVitae(Long id, String title, String summary){
        this.id = id;
        this.title = title;
        this.summary = summary;
    }

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

    public List<Education> getEducations() {
        return educations;
    }

    public void addEducation(Education education) {
        if(this.educations.contains(education)){
            return;
        }
        this.educations.add(education);
        education.setCurriculumVitae(this);
    }

    public void removeEducation(Education education){
        if(!this.educations.contains(education)){
            return;
        }
        this.educations.remove(education);
        education.setCurriculumVitae(null);
    }

    public void addExperience(Experience experience) {
        if(this.experiences.contains(experience)){
            return;
        }
        this.experiences.add(experience);
        experience.setCurriculumVitae(this);
    }

    public void removeExperience(Experience experience){
        if(!this.experiences.contains(experience)){
            return;
        }
        this.educations.remove(experience);
        experience.setCurriculumVitae(null);
    }

    public void addAward(Award award) {
        if(this.awards.contains(award)){
            return;
        }
        this.awards.add(award);
        award.setCurriculumVitae(this);
    }

    public void removeAward(Award award){
        if(!this.experiences.contains(award)){
            return;
        }
        this.awards.remove(award);
        award.setCurriculumVitae(null);
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof CurriculumVitae))
            return false;

        final CurriculumVitae a = (CurriculumVitae)object;

        if (id != null && a.getId() != null) {
            return id.equals(a.getId());
        }
        return false;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        //prevent endless loop
        if (sameUserAsFormer(user))
            return ;
        //set new owner
        User oldOwner = this.user;
        this.user = user;
        //remove from the old owner
        if (oldOwner!=null)
            oldOwner.removeCurriculumVitae(this);
        //set myself into new owner
        if (user!=null)
            user.addCurriculumVitae(this);
    }

    private boolean sameUserAsFormer(User newOwner) {
        return this.user==null? newOwner == null : this.user.equals(newOwner);
    }
}
