package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.response.EducationViewModel;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationViewModelMapper implements ModelFactory<Education, EducationViewModel> {

    @Override
    public EducationViewModel create(Education education) {
        EducationViewModel result = new EducationViewModel();

        result.setAreaOfStudy(education.getAreaOfStudy());
        result.setDescription(education.getDescription());
        result.setId(education.getId());
        result.setTitle(education.getTitle());
        result.setCity(education.getCity().getId());

        return result;
    }
}
