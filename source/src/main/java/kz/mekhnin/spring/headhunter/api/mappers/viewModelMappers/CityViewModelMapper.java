package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.CityViewModel;
import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import org.springframework.stereotype.Component;

@Component
public class CityViewModelMapper implements ModelFactory<City, CityViewModel> {
    @Override
    public CityViewModel create(City city) {
        CityViewModel result = new CityViewModel();

        result.setId(city.getId());
        result.setName(city.getName());

        return result;
    }
}
