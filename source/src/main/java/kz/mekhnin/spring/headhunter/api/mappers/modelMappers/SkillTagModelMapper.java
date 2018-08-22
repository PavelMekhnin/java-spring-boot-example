package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.SkillTagVewModel;
import kz.mekhnin.spring.headhunter.data.entities.SkillTag;
import org.springframework.stereotype.Component;

@Component
public class SkillTagModelMapper implements ModelMapper <SkillTagVewModel, SkillTag>{
    @Override
    public void Map(SkillTagVewModel skillTagVewModel, SkillTag skillTag) {
        skillTag.setTitle(skillTagVewModel.getTitle());

    }
}
