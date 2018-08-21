package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.AccountCredentialsMaper;
import kz.mekhnin.spring.headhunter.api.viewModels.AccountCredentialsViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.AuthApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountCredentialsMaper accountCredentialsMaper;

    @Autowired
    private AuthApplicationService authApplicationService;

    @PostMapping("/signUp")
    public @ResponseBody String signUp(@RequestBody AccountCredentialsViewModel credentials)
    {
        User user = accountCredentialsMaper.create(credentials);

        return authApplicationService.signUp(user);
    }

    @PostMapping("/signIn")
    public @ResponseBody String signIn(@RequestBody AccountCredentialsViewModel credentials)
    {
        return authApplicationService.signIn(credentials.getLogin(), credentials.getPassword());
    }
}
