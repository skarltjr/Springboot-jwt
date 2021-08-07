package com.example.demo.module.account;

import com.example.demo.module.IndexController;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class AccountResource extends EntityModel<Account> {
    public static EntityModel<Account> modelOf(Account account) {
        EntityModel<Account> resource = EntityModel.of(account);
        resource.add(linkTo(IndexController.class).slash("hello").withRel("hello"));
        return resource;
    }
}
