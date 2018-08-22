package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.response.ExperienceViewModel;
import kz.mekhnin.spring.headhunter.data.entities.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceViewModelMapper implements ModelFactory<Experience, ExperienceViewModel> {

    @Override
    public ExperienceViewModel create(Experience experience) {
        ExperienceViewModel result = new ExperienceViewModel();

        result.setId(experience.getId());
        result.setDescription(experience.getDescription());
        result.setStartDate(experience.getStartDate());
        result.setEndDate(experience.getEndDate());
        result.setTitle(experience.getTitle());
        result.setCompanyTitle(experience.getCompanyTitle());
        result.setCity(experience.getCity().getId());

        return result;
    }
}
