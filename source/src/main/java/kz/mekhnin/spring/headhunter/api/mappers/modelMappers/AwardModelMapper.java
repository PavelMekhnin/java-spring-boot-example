package kz.mekhnin.spring.headhunter.api.mappers.modelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveAwardRequest;
import kz.mekhnin.spring.headhunter.data.entities.Award;
import org.springframework.stereotype.Component;

@Component
public class AwardModelMapper implements ModelMapper<SaveAwardRequest, Award> {
    @Override
    public void Map(SaveAwardRequest awardViewModel, Award award) {
        award.setDescription(awardViewModel.getDescription());
        award.setTitle(awardViewModel.getTitle());
    }
}
