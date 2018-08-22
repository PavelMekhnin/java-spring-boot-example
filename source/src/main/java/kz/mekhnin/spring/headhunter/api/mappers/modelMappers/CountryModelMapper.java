package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.CountryViewModel;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryModelMapper implements ModelMapper<CountryViewModel, Country> {
    @Override
    public void Map(CountryViewModel countryViewModel, Country country) {
        country.setId(countryViewModel.getId());
        country.setName(countryViewModel.getName());
    }
}
