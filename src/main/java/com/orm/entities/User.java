package com.orm.entities;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String contact;
    private String username;
    private String password;
}
