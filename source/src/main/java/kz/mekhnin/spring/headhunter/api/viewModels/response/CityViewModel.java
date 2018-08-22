package kz.mekhnin.spring.headhunter.api.viewModels.response;

public class CityViewModel {

    private Long id;

    private String name;

    private CountryViewModel country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryViewModel getCountry() {
        return country;
    }

    public void setCountry(CountryViewModel country) {
        this.country = country;
    }
}
