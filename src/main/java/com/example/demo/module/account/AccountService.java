package com.example.demo.module.account;

import com.example.demo.module.account.dto.SignUpForm;
import com.example.demo.module.common.ErrorCode;
import com.example.demo.module.common.exception.PasswordNotMatchException;
import com.example.demo.module.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new UserNotFoundException(
                ErrorCode.USER_NOT_FOUND.getErrorMessage(), ErrorCode.USER_NOT_FOUND.getErrorCode()
        ));
        return new UserAccount(account);
    }

    public Account signUp(SignUpForm form) {
        Account build = Account.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        Account save = accountRepository.save(build);
        return save;
    }

    public Account login(SignUpForm form) {
        Account account = accountRepository.findByUsername(form.getUsername()).orElseThrow(() -> new UserNotFoundException(
                ErrorCode.USER_NOT_FOUND.getErrorMessage(), ErrorCode.USER_NOT_FOUND.getErrorCode()
        ));
        if (!passwordEncoder.matches(form.getPassword(), account.getPassword())) {
            throw new PasswordNotMatchException(ErrorCode.PASSWORD_NOT_MATCH.getErrorMessage(),
                    ErrorCode.PASSWORD_NOT_MATCH.getErrorCode());
        }
        return account;
    }
}
