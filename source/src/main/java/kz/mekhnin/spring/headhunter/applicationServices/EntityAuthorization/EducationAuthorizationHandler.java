package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import org.springframework.stereotype.Component;

@Component
public class EducationAuthorizationHandler implements EntityAuthorizationHandler<Education> {
    @Override
    public boolean authorize(Long currentUserId, Education education, ActionType[] actions) {
        for (ActionType action : actions
        ) {
            if (currentUserId == education.getUser().getId()) {
                continue;
            }

            if (action != ActionType.Read) {
                return false;
            }
        }

        return true;
    }
}
