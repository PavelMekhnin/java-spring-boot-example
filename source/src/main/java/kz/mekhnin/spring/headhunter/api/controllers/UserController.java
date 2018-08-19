package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.mappers.UserMapper;
import kz.mekhnin.spring.headhunter.api.mappers.UserViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.UserViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.UserApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserViewModelMapper userViewModelMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public @ResponseBody UserViewModel getUser(@PathVariable Long id){
        User user = userApplicationService.getUser(id);

        if(user == null){
            throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
        }

        UserViewModel viewModel = userViewModelMapper.create(user);

        return viewModel;
    }

    @GetMapping
    public @ResponseBody List<UserViewModel> getUsers(){
        List<User> users = userApplicationService.getUsers();

        List<UserViewModel> viewModels = users.parallelStream()
                .map(x -> userViewModelMapper.create(x))
                .collect(Collectors.toList());

        return viewModels;
    }

    @PostMapping("/{id}")
    public @ResponseBody UserViewModel updateUser(@PathVariable Long id,@RequestBody UserViewModel viewModel){
        User user = userApplicationService.getUser(id);

        userMapper.Map(viewModel, user);

        User result = userApplicationService.saveUser(user);

        UserViewModel resultViewModel = userViewModelMapper.create(result);

        return resultViewModel;
    }
}
