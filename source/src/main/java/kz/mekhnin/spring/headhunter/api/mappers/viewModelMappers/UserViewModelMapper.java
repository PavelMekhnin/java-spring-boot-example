package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.response.UserViewModel;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserViewModelMapper implements ModelFactory<User, UserViewModel> {

    @Override
    public UserViewModel create(User user) {
        UserViewModel result = new UserViewModel();

        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setBirthDate(user.getBirthDate());

        return result;
    }
}
