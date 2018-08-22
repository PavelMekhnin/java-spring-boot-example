package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveEducationRequest;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import org.springframework.stereotype.Component;

@Component
public class EducationModelMapper implements ModelMapper<SaveEducationRequest, Education> {

    @Override
    public void Map(SaveEducationRequest educationViewModel, Education education) {
        education.setAreaOfStudy(educationViewModel.getAreaOfStudy());
        education.setDescription(educationViewModel.getDescription());
        education.setTitle(educationViewModel.getTitle());
        City city = new City();
        city.setId(educationViewModel.getCityId());
        education.setCity(city);
    }
}
