package me.yunchat.web.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yunchat.domain.user.application.UserService;
import me.yunchat.domain.user.domain.User;
import me.yunchat.domain.user.dto.UserSaveDto;
import me.yunchat.web.exception.NotSamePasswordException;
import me.yunchat.web.dto.SignUpRequestDto;
import me.yunchat.web.exception.LoginErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignUpRequestDto request) {

        log.info("Signup Start - nickname : {}", request.getNickname());

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.info("Signup fail(not same password) - nickname : {}", request.getNickname());
            throw new NotSamePasswordException(LoginErrorCode.NOT_SAME_PASSWORD);
        }

        log.info("Signup End - nickname : {}", request.getNickname());
        UserSaveDto userSaveDto = new UserSaveDto(request.getNickname(), passwordEncoder.encode(request.getPassword()));
        return userService.save(userSaveDto);
    }
}
