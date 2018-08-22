package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.UserAuthorizationHandler;
import kz.mekhnin.spring.headhunter.data.entities.User;
import kz.mekhnin.spring.headhunter.usercontext.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationService extends BaseApplicationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorizationHandler authorizationHandler;

    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
        }

        boolean hasAccess = authorizationHandler.authorize(getCurrentUser().getId(), user.get(), new ActionType[]{ActionType.Read});
        if(!hasAccess){
            throw new CustomException("Denied access to read user.", HttpStatus.UNAUTHORIZED);
        }

        return user.get();
    }

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();

        for (User user: users
             ) {
            boolean hasAccess = authorizationHandler.authorize(getCurrentUser().getId(), user, new ActionType[]{ActionType.Read});
            if (!hasAccess) {
                throw new CustomException("Denied access to read users.", HttpStatus.UNAUTHORIZED);
            }
        }

        return users;
    }

    public User saveUser(User user){
        Optional<User> existUser = userRepository.findById(user.getId());

        if(existUser.isPresent()){
            boolean hasAccess = authorizationHandler.authorize(getCurrentUser().getId(), user, new ActionType[]{ActionType.Update});
            if(!hasAccess){
                throw new CustomException("Denied access to update user.", HttpStatus.UNAUTHORIZED);
            }
        }

        User result = userRepository.save(user);

        return result;
    }
}
