package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.LanguageSkillViewModel;
import kz.mekhnin.spring.headhunter.data.entities.LanguageSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageSkillViewModelMapper implements ModelFactory<LanguageSkill, LanguageSkillViewModel> {

    @Autowired
    private LanguageViewModelMapper languageViewModelMapper;

    @Override
    public LanguageSkillViewModel create(LanguageSkill languageSkill) {
        LanguageSkillViewModel result = new LanguageSkillViewModel();

        result.setId(languageSkill.getId());
        result.setGrade(languageSkill.getGrade());
        result.setLanguage(languageViewModelMapper.create(languageSkill.getLanguage()));

        return result;
    }
}
