package com.example.demo.module.account;

import com.example.demo.infra.JwtTokenProvider;
import com.example.demo.module.account.dto.SignUpForm;
import com.example.demo.module.account.dto.Token;
import com.example.demo.module.account.vallidator.SignUpFormValidator;
import com.example.demo.module.errors.ErrorResource;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final SignUpFormValidator validator;
    private final JwtTokenProvider provider;

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody @Valid SignUpForm form, Errors errors) {
        validator.validate(form, errors);
        if (errors.hasErrors()) {
            EntityModel<Errors> error = ErrorResource.modelOf(errors);
            return ResponseEntity.badRequest().body(error);
        }
        Account account = accountService.signUp(form);
        return ResponseEntity.ok(AccountResource.modelOf(account));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid SignUpForm form, Errors errors) {
        if (errors.hasErrors()) {
            EntityModel<Errors> error = ErrorResource.modelOf(errors);
            return ResponseEntity.badRequest().body(error);
        }
        Account account = accountService.login(form);
        String token = provider.createToken(account.getUsername());
        return ResponseEntity.ok(LoginResource.modelOf(token, account));
    }

    @PostMapping("/logoutReq")
    public ResponseEntity logout(@RequestBody Token token) {
        if (!provider.validateToken(token.getToken())) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        try {
            if(provider.validateToken(token.getToken())) {
                provider.logoutTokens(token.getToken());
            }
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
