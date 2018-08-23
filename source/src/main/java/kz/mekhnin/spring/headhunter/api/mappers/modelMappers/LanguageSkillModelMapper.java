package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.LanguageSkillViewModel;
import kz.mekhnin.spring.headhunter.data.entities.LanguageSkill;
import org.springframework.stereotype.Component;

@Component
public class LanguageSkillModelMapper implements ModelMapper<LanguageSkillViewModel, LanguageSkill> {
    @Override
    public void Map(LanguageSkillViewModel languageSkillViewModel, LanguageSkill languageSkill) {
        languageSkill.setGrade(languageSkillViewModel.getGrade());
    }
}
