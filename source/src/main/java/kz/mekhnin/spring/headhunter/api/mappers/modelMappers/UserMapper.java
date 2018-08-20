package kz.mekhnin.spring.headhunter.api.mappers;

import kz.mekhnin.spring.Common.interfaces.ModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.UserViewModel;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ModelMapper<UserViewModel, User> {

    @Override
    public void Map(UserViewModel userViewModel, User user) {
        user.setName(userViewModel.getName());
        user.setEmail(userViewModel.getEmail());
        user.setBirthDate(userViewModel.getBirthDate());
    }
}
