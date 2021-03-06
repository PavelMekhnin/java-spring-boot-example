package kz.mekhnin.spring.headhunter.data.entities.addressing;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Countries")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(
            mappedBy = "country",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            targetEntity = City.class
    )
    private List<City> cities = new ArrayList<>();

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

    public List<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        if(this.cities.contains(city)){
            return;
        }
        this.cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city){
        if(!this.cities.contains(city)){
            return;
        }
        this.cities.remove(city);
        city.setCountry(null);
    }
}
