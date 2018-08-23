package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.data.entities.Experience;
import org.springframework.stereotype.Component;

@Component
public class ExperienceAuthorizationHandler implements EntityAuthorizationHandler<Experience> {
    @Override
    public boolean authorize(Long currentUserId, Experience experience, ActionType[] actions) {
        for (ActionType action : actions
        ) {
            if (currentUserId == experience.getUser().getId()) {
                continue;
            }

            if (action != ActionType.Read) {
                return false;
            }
        }

        return true;
    }
}
