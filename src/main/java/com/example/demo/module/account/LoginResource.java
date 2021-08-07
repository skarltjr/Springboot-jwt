package com.example.demo.module.account;

import com.example.demo.module.IndexController;
import com.example.demo.module.account.dto.LoginResult;
import org.springframework.hateoas.EntityModel;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class LoginResource extends EntityModel<Map<String,Account>> {
    public static EntityModel<LoginResult> modelOf(String token, Account account) {
        EntityModel<LoginResult> resource = EntityModel.of(new LoginResult(account.getUsername(), token));
        resource.add(linkTo(IndexController.class).slash("hello").withRel("hello"));
        return resource;
    }
}
