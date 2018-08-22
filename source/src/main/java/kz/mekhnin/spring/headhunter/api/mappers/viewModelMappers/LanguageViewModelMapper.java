package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.response.LanguageViewModel;
import kz.mekhnin.spring.headhunter.data.entities.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageViewModelMapper implements ModelFactory<Language, LanguageViewModel> {
    @Override
    public LanguageViewModel create(Language language) {
        LanguageViewModel result = new LanguageViewModel();

        result.setTitle(language.getTitle());
        result.setId(language.getId());

        return result;
    }
}
