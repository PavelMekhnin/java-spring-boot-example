package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityAuthorizationProvider<TEntity> {

    @Autowired
    private EntityAuthorizationHandler<TEntity>[] handlers;

    public boolean authorize(Long currentUserid, TEntity entity, ActionType[] actions){

        for (EntityAuthorizationHandler handler: handlers
             ) {
            for (ActionType action: actions
                 ) {
                boolean success = handler.authorize(currentUserid, entity, action);
                if (!success){
                    return false;
                }
            }
        }

        return true;
    }
}
