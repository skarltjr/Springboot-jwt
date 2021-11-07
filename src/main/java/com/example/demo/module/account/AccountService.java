package com.example.demo.module.account;

import com.example.demo.module.account.dto.SignUpForm;
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
        Optional<Account> account = accountRepository.findByUsername(name);
        account.orElseThrow(() -> new UsernameNotFoundException(name + "유저는 존재하지 않습니다."));
        return new UserAccount(account.get());
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
        Optional<Account> byUsername = accountRepository.findByUsername(form.getUsername());
        byUsername.orElseThrow(() -> new UsernameNotFoundException(form.getUsername() + "유저는 존재하지 않습니다."));
        Account account = byUsername.get();
        if (!passwordEncoder.matches(form.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        return account;
    }
}
