package kz.mekhnin.spring.headhunter.api.viewModels.request;

import java.util.Date;

public class SaveExperienceRequest {
    private Long id;

    private String title;

    private String description;

    private String companyTitle;

    private Date startDate;

    private Date endDate;

    private Long cityId;

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

    public Long getCity() {
        return cityId;
    }

    public void setCity(Long cityId) {
        this.cityId = cityId;
    }
}
