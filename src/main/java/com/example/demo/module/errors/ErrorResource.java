package com.example.demo.module.errors;

import com.example.demo.module.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.Errors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ErrorResource extends EntityModel<Errors> {
    public static EntityModel<Errors> modelOf(Errors errors) {
        EntityModel<Errors> errorResource = EntityModel.of(errors);
        errorResource.add(linkTo(IndexController.class).slash("hello").withRel("hello"));
        return errorResource;
    }
}
