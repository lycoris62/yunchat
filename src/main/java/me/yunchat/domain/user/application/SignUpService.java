package me.yunchat.domain.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import me.yunchat.domain.user.dto.SignUpDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean signup(SignUpDto signUpDto) {

        if (!signUpDto.getPassword().equals(signUpDto.getConfirmPassword())) {
            log.info("Signup fail(not same password) - nickname : {}", signUpDto.getNickname());
            return false;
        }

        userRepository.save(User.builder()
                .nickname(signUpDto.getNickname())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build());

        log.info("Signup End - nickname : {}", signUpDto.getNickname());
        return true;
    }
}
