package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveExperienceRequest;
import kz.mekhnin.spring.headhunter.data.entities.Experience;
import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import org.springframework.stereotype.Component;

@Component
public class ExperienceModelMapper implements ModelMapper<SaveExperienceRequest, Experience> {
    @Override
    public void Map(SaveExperienceRequest experienceViewModel, Experience experience) {
        experience.setCompanyTitle(experienceViewModel.getCompanyTitle());
        experience.setDescription(experienceViewModel.getDescription());
        experience.setStartDate(experienceViewModel.getStartDate());
        experience.setEndDate(experienceViewModel.getEndDate());
        experience.setTitle(experienceViewModel.getCompanyTitle());
        City city = new City();
        city.setId(experienceViewModel.getCity());
        experience.setCity(city);
    }
}
