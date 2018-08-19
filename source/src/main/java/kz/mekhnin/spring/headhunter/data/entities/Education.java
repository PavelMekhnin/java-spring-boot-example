package kz.mekhnin.spring.headhunter.data.entities;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Educations")
public class Education {
    @Id
    @GeneratedValue
    private Long id;

    private Long cvId;

    @NotBlank
    private String title;

    private String areaOfStudy;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = City.class)
    @JoinColumn(name = "cityId", referencedColumnName="id", nullable=true, unique=true)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CurriculumVitae.class)
    @JoinColumn(name = "curriculumVitaeId",referencedColumnName="id", nullable=false, unique=true)
    private CurriculumVitae curriculumVitae;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAreaOfStudy() {
        return areaOfStudy;
    }

    public void setAreaOfStudy(String areaOfStudy) {
        this.areaOfStudy = areaOfStudy;
    }

    public Long getCvId() {
        return cvId;
    }

    public void setCvId(Long cvId) {
        this.cvId = cvId;
    }
}
