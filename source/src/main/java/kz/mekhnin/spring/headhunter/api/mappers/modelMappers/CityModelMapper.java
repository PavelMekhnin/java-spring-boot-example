package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.CityViewModel;
import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import org.springframework.stereotype.Component;

@Component
public class CityModelMapper implements ModelMapper<CityViewModel, City> {
    @Override
    public void Map(CityViewModel cityViewModel, City city) {
        city.setId(cityViewModel.getId());
        city.setName(cityViewModel.getName());
    }
}
