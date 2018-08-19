package kz.mekhnin.spring.headhunter.data.entities;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "Experiences")
public class Experience {
    @Id
    @GeneratedValue
    private Long id;

    private Long cvId;

    @NotBlank
    private String title;

    private String description;

    private String companyTitle;

    @NotNull
    private Date startDate;

    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CurriculumVitae.class)
    @JoinColumn(name = "curriculumVitaeId",referencedColumnName="id", nullable=false, unique=true)
    private CurriculumVitae curriculumVitae;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = City.class)
    @JoinColumn(name = "cityId", referencedColumnName="id", nullable=true, unique=true)
    private City city;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getCvId() {
        return cvId;
    }

    public void setCvId(Long cvId) {
        this.cvId = cvId;
    }
}
