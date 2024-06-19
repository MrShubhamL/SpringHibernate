package com.orm.servies;

import com.orm.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public interface UserService {
    Serializable createUser(User user);
}
