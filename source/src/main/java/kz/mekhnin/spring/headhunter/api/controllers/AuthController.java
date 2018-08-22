package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.AccountCredentialsMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.AccountCredentialsViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.AuthApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountCredentialsMapper accountCredentialsMaper;

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
