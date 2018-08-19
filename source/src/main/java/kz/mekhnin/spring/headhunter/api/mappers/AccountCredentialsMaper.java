package kz.mekhnin.spring.headhunter.api.mappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.AccountCredentialsViewModel;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class AccountCredentialsMaper implements ModelFactory<AccountCredentialsViewModel, User> {

    @Override
    public User create(AccountCredentialsViewModel accountCredentialsViewModel) {
        User user = new User();

        user.setEmail(accountCredentialsViewModel.getLogin());
        user.setPassword(accountCredentialsViewModel.getPassword());

        return user;
    }
}
