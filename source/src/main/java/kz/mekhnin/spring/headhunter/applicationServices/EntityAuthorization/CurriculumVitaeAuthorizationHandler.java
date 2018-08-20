package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import org.springframework.stereotype.Component;

@Component
public class CurriculumVitaeAthorizationHandler implements EntityAuthorizationHandler<CurriculumVitae>{
    @Override
    public boolean authorize(
            Long currentUserId,
            CurriculumVitae curriculumVitae,
            ActionType action
    ) {
        // Owner has full access
        if(currentUserId == curriculumVitae.getUser().getId()){
            return true;
        }

        // others can read only
        if (action != ActionType.Read){
            return false;
        }

        return true;
    }
}
