package kz.mekhnin.spring.headhunter.data.entities;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


@Table(name = "Users")
@Entity
public class User{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @NotBlank
    private String password;

    private Date birthDate;

    @NotBlank
    private String email;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            targetEntity = Award.class,
            cascade={CascadeType.REMOVE}
    )
    private List<Award> awards;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            targetEntity = Education.class,
            cascade={CascadeType.REMOVE}
    )
    private List<Education> educations;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            targetEntity = Experience.class,
            cascade={CascadeType.REMOVE}
    )
    private List<Experience> experiences;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            targetEntity = CurriculumVitae.class,
            cascade={CascadeType.REMOVE}
    )
    private List<CurriculumVitae> curriculumVitaes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public List<CurriculumVitae> getCurriculumVitaes() {
        return curriculumVitaes;
    }


    public List<Award> getAwards() {
        return awards;
    }


    public List<Education> getEducations() {
        return educations;
    }


    public List<Experience> getExperiences() {
        return experiences;
    }

    public void addCurriculumVitae(CurriculumVitae cv){
        if(this.curriculumVitaes.contains(cv)){
            return;
        }
        this.curriculumVitaes.add(cv);
    }

    public void removeCurriculumVitae(CurriculumVitae cv){
        if(!this.curriculumVitaes.contains(cv)){
            return;
        }
        this.curriculumVitaes.remove(cv);
        cv.setUser(null);
    }

    public void addCurriculumVitae(Experience ex){
        if(this.experiences.contains(ex)){
            return;
        }
        this.experiences.add(ex);
        ex.setUser(this);
    }

    public void removeCurriculumVitae(Experience ex){
        if(!this.experiences.contains(ex)){
            return;
        }
        this.experiences.remove(ex);
        ex.setUser(null);
    }

    public void addCurriculumVitae(Education ed){
        if(this.educations.contains(ed)){
            return;
        }
        this.educations.add(ed);
        ed.setUser(this);
    }

    public void removeCurriculumVitae(Education ed){
        if(!this.educations.contains(ed)){
            return;
        }
        this.educations.remove(ed);
        ed.setUser(null);
    }

    public void addCurriculumVitae(Award award){
        if(this.awards.contains(award)){
            return;
        }
        this.awards.add(award);
        award.setUser(this);
    }

    public void removeCurriculumVitae(Award award){
        if(!this.awards.contains(award)){
            return;
        }
        this.awards.remove(award);
        award.setUser(null);
    }

    public void setCurriculumVitaes(List<CurriculumVitae> curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }
}
