package com.example.demorest.models.forms;

import com.example.demorest.models.entities.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserCreateForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(List.of("USER"));
        return user;
    }
}
