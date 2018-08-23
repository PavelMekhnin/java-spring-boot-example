package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveCurriculumVitaeRequest;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import org.springframework.stereotype.Component;

@Component
public class CurriculumVitaeModelMapper implements ModelMapper<SaveCurriculumVitaeRequest, CurriculumVitae> {

    @Override
    public void Map(SaveCurriculumVitaeRequest model, CurriculumVitae curriculumVitae) {
        curriculumVitae.setSummary(model.getSummary());
        curriculumVitae.setTitle(model.getTitle());
    }
}
