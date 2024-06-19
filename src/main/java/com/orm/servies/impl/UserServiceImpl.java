package com.orm.servies.impl;

import com.orm.entities.User;
import com.orm.servies.UserService;
import lombok.Data;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Component
@Data
public class UserServiceImpl implements UserService {
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(readOnly = false)
    public Serializable createUser(User user) {
        return hibernateTemplate.save(user);
    }
}
