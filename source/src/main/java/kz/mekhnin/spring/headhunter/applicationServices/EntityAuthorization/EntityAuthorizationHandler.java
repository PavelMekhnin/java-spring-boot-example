package kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization;

import kz.mekhnin.spring.headhunter.api.security.ActionType;

public interface EntityAuthorizationHandler<TEntity> {

    boolean authorize(Long currentUserId, TEntity entity, ActionType[] actions);
}
