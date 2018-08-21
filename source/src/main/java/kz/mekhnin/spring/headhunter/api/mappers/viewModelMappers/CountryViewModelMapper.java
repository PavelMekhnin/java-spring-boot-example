package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.CountryViewModel;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryViewModelMapper implements ModelFactory<Country, CountryViewModel> {

    @Override
    public CountryViewModel create(Country country) {
        CountryViewModel result = new CountryViewModel();

        result.setId(country.getId());
        result.setName(country.getName());

        return result;
    }
}
