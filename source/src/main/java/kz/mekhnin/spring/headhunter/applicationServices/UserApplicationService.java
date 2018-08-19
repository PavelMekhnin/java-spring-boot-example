package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.EntityAuthorizationProvider;
import kz.mekhnin.spring.headhunter.data.entities.User;
import kz.mekhnin.spring.headhunter.usercontext.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApplicationService extends BaseApplicationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityAuthorizationProvider<User> authorizationProvider;

    public User getUser(Long id){
        User user = userRepository.getOne(id);

        boolean hasAccess = authorizationProvider.authorize(getCurrentUser().getId(), user, new ActionType[]{ActionType.Read});
        if(!hasAccess){
            throw new CustomException("Denied access to read user.", HttpStatus.UNAUTHORIZED);
        }

        return user;
    }

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();

        for (User user: users
             ) {
            boolean hasAccess = authorizationProvider.authorize(getCurrentUser().getId(), user, new ActionType[]{ActionType.Read});
            if (!hasAccess) {
                throw new CustomException("Denied access to read users.", HttpStatus.UNAUTHORIZED);
            }
        }

        return users;
    }

    public User saveUser(User user){
        User existUser = userRepository.getOne(user.getId());

        if(existUser != null){
            boolean hasAccess = authorizationProvider.authorize(getCurrentUser().getId(), user, new ActionType[]{ActionType.Update});
            if(!hasAccess){
                throw new CustomException("Denied access to update user.", HttpStatus.UNAUTHORIZED);
            }
        }

        User result = userRepository.save(user);

        return result;
    }
}
