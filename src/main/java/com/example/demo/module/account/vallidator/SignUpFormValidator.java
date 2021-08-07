package com.example.demo.module.account.vallidator;


import com.example.demo.module.account.AccountRepository;
import com.example.demo.module.account.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {
    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpForm form = (SignUpForm) o;
        if (accountRepository.existsByUsername(form.getUsername())) {
            errors.rejectValue("username", "invalid username", new Object[]{form.getUsername()}, "이미 사용중인 이름입니다");
        }
    }
}
