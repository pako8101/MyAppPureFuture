package com.pureFutureApp.service;

import com.pureFutureApp.model.UserLoginBindingModel;
import com.pureFutureApp.model.UserRegisterBindingModel;

public interface UserService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

}
