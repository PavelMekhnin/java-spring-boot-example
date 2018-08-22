package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.stereotype.Component;

// authorization handler for entity user
@Component
public class UserAuthorizationHandler implements EntityAuthorizationHandler<User> {

    @Override
    public boolean authorize(Long currentUserId, User user, ActionType[] actions) {

        for (ActionType action: actions
             ) {
            //user can do everything with himself
            if(user.getId() == currentUserId){
                return true;
            }

            // user can read only another users
            if(action != ActionType.Read){
                return false;
            }
        }

        return true;
    }
}
