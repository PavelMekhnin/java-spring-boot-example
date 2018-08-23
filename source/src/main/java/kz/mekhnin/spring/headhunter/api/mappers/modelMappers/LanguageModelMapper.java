package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.LanguageViewModel;
import kz.mekhnin.spring.headhunter.data.entities.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageModelMapper implements ModelMapper<LanguageViewModel, Language> {
    @Override
    public void Map(LanguageViewModel languageViewModel, Language language) {
        language.setId(languageViewModel.getId());
        language.setTitle(languageViewModel.getTitle());
    }
}
