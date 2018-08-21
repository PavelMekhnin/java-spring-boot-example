package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.SkillTagVewModel;
import kz.mekhnin.spring.headhunter.data.entities.SkillTag;
import org.springframework.stereotype.Component;

@Component
public class SkillTagViewModelMapper implements ModelFactory<SkillTag, SkillTagVewModel> {

    @Override
    public SkillTagVewModel create(SkillTag skillTag) {
        SkillTagVewModel result = new SkillTagVewModel();

        result.setId(skillTag.getId());
        result.setTitle(skillTag.getTitle());

        return result;
    }
}
