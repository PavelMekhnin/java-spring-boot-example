package kz.mekhnin.spring.headhunter.api.viewModels;

import java.util.Date;

public class ExperienceViewModel {
    private Long id;

    private String title;

    private String description;

    private String companyTitle;

    private Date startDate;

    private Date endDate;

    private CityViewModel city;

    private CountryViewModel country;

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

    public CityViewModel getCity() {
        return city;
    }

    public void setCity(CityViewModel city) {
        this.city = city;
    }

    public CountryViewModel getCountry() {
        return country;
    }

    public void setCountry(CountryViewModel country) {
        this.country = country;
    }
}
