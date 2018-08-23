package kz.mekhnin.spring.headhunter.data.entities;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    private String areaOfStudy;

    private String description;

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = City.class,
            cascade = {CascadeType.MERGE, CascadeType.DETACH}
    )
    @JoinColumn(
            name = "cityId",
            referencedColumnName = "id",
            unique = true
    )
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curriculumVitaeId",
            referencedColumnName = "id"
    )
    private CurriculumVitae curriculumVitae;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    public Education(){}

    public Education(String title, String areaOfStudy, String description){
        this.title =  title;
        this.areaOfStudy = areaOfStudy;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAreaOfStudy() {
        return areaOfStudy;
    }

    public void setAreaOfStudy(String areaOfStudy) {
        this.areaOfStudy = areaOfStudy;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        //prevent endless loop
        if (sameAsFormer(curriculumVitae))
            return ;
        //set new owner
        CurriculumVitae oldOwner = this.curriculumVitae;
        this.curriculumVitae = curriculumVitae;
        //remove from the old owner
        if (oldOwner!=null)
            oldOwner.removeEducation(this);
        //set myself into new owner
        if (curriculumVitae!=null)
            curriculumVitae.addEducation(this);
    }
    private boolean sameAsFormer(CurriculumVitae newOwner) {
        return this.curriculumVitae==null? newOwner == null : this.curriculumVitae.equals(newOwner);
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Education))
            return false;

        final Education a = (Education)object;

        if (id != null && a.getId() != null) {
            return id.equals(a.getId());
        }
        return false;
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

    public void setCity(City city) {
        //prevent endless loop
        if (sameAsCityFormer(city))
            return ;
        //set new owner
        City oldOwner = this.city;
        this.city = city;
        //remove from the old owner
        if (oldOwner!=null)
            oldOwner.removeEducation(this);
        //set myself into new owner
        if (city!=null)
            city.addEducation(this);
    }

    public City getCity(){
        return this.city;
    }

    private boolean sameAsCityFormer(City newOwner) {
        return this.city==null? newOwner == null : this.city.equals(newOwner);
    }
}
