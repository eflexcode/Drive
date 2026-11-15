package com.ifeanyi.userserivce_drive.controller;

import com.ifeanyi.userserivce_drive.entity.User;
import com.ifeanyi.userserivce_drive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GraphqlUserController {

    private final UserService userService;

    @QueryMapping
    public User getUser(@Argument int id) {
        return userService.getUser(id);
    }

}
