package kz.mekhnin.spring.headhunter.data.entities;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Experiences")
public class Experience {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private String description;

    private String companyTitle;

    @NotNull
    private Date startDate;

    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curriculumVitaeId",
            referencedColumnName = "id"
    )
    private CurriculumVitae curriculumVitae;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userID",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = City.class,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "cityId",
            referencedColumnName = "id",
            unique = true
    )
    private City city;

    public Experience(){}

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

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
            oldOwner.removeExperience(this);
        //set myself into new owner
        if (curriculumVitae!=null)
            curriculumVitae.addExperience(this);
    }
    private boolean sameAsFormer(CurriculumVitae newOwner) {
        return this.curriculumVitae==null? newOwner == null : this.curriculumVitae.equals(newOwner);
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
            oldOwner.removeExperience(this);
        //set myself into new owner
        if (city!=null)
            city.addExperience(this);
    }

    private boolean sameAsCityFormer(City newOwner) {
        return this.city==null? newOwner == null : this.city.equals(newOwner);
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Experience))
            return false;

        final Experience a = (Experience)object;

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

    public City getCity() {
        return city;
    }
}
