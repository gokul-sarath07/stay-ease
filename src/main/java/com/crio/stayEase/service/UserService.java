package com.crio.stayEase.service;

import com.crio.stayEase.dto.CreateUser;
import com.crio.stayEase.entity.User;

public interface UserService {
    User registerUser(CreateUser createUser);
    User findByEmail(String email);
    User saveUser(User user);
}
