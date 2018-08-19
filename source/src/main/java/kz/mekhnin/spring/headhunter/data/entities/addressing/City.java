package kz.mekhnin.spring.headhunter.data.entities.addressing;

import kz.mekhnin.spring.headhunter.data.entities.Education;
import kz.mekhnin.spring.headhunter.data.entities.Experience;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Cities")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name = "countryId",referencedColumnName="id",nullable=false,unique=true)
    private Country country;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY,
            orphanRemoval = false
            ,targetEntity = Education.class
    )
    private List<Education> educations;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY,
            orphanRemoval = false
            ,targetEntity = Experience.class
    )
    private List<Experience> experiences;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
