package kz.mekhnin.spring.headhunter.api.viewModels.response;

import java.util.List;

public class CountryViewModel {

    private Long id;

    private String name;

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
}
