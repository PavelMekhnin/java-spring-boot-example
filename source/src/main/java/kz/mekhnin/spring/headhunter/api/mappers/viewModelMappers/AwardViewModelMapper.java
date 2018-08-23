package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.response.AwardViewModel;
import kz.mekhnin.spring.headhunter.data.entities.Award;
import org.springframework.stereotype.Component;

@Component
public class AwardViewModelMapper implements ModelFactory<Award, AwardViewModel> {
    @Override
    public AwardViewModel create(Award award) {
        AwardViewModel result = new AwardViewModel();

        result.setId(award.getId());
        result.setTitle(award.getTitle());
        result.setDescription(award.getDescription());

        return result;
    }
}
