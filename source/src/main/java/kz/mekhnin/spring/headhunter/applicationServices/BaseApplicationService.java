package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.security.HeadHunterUser;
import kz.mekhnin.spring.headhunter.usercontext.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseApplicationService {

    @Autowired
    protected UserRepository userRepository;

    protected HeadHunterUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return (HeadHunterUser) authentication.getPrincipal();
        }

        return null;
    }
}
