package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.data.entities.Award;
import org.springframework.stereotype.Component;

@Component
public class AwardAuthorizationHandler implements EntityAuthorizationHandler<Award>{
    @Override
    public boolean authorize(Long currentUserId, Award award, ActionType[] actions) {
        for (ActionType action : actions
        ) {
            if (currentUserId == award.getUser().getId()) {
                continue;
            }

            if (action != ActionType.Read) {
                return false;
            }
        }

        return true;
    }
}
