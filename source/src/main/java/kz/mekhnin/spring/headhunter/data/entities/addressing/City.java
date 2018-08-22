package kz.mekhnin.spring.headhunter.data.entities.addressing;

import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import kz.mekhnin.spring.headhunter.data.entities.Experience;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cities")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String name;

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = Country.class
    )
    @JoinColumn(
            name = "countryId",
            referencedColumnName="id",
            nullable=false
    )
    private Country country;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            targetEntity = Education.class
    )
    private List<Education> educations = new ArrayList<>();

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            targetEntity = Experience.class
    )
    private List<Experience> experiences = new ArrayList<>();

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

    public void addExperience(Experience experience) {
        if(this.experiences.contains(experience)){
            return;
        }
        this.experiences.add(experience);
        experience.setCity(this);
    }

    public void removeExperience(Experience experience){
        if(!this.experiences.contains(experience)){
            return;
        }
        this.experiences.remove(experience);
        experience.setCity(null);
    }

    public void addEducation(Education education) {
        if(this.educations.contains(education)){
            return;
        }
        this.educations.add(education);
        education.setCity(this);
    }

    public void removeEducation(Education education){
        if(!this.educations.contains(education)){
            return;
        }
        this.educations.remove(education);
        education.setCity(null);
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

    public void setCountry(Country country) {
        //prevent endless loop
        if (sameAsCountryFormer(country))
            return ;
        //set new owner
        Country oldOwner = this.country;
        this.country = country;
        //remove from the old owner
        if (oldOwner!=null)
            oldOwner.removeCity(this);
        //set myself into new owner
        if (country!=null)
            country.addCity(this);
    }

    public Country getCountry(){
        return this.country;
    }

    private boolean sameAsCountryFormer(Country newOwner) {
        return this.country==null? newOwner == null : this.country.equals(newOwner);
    }
}
