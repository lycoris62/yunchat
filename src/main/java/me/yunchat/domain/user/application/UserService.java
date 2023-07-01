package me.yunchat.domain.user.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import me.yunchat.domain.user.dto.SignUpRequestDto;
import me.yunchat.domain.user.exception.NoUserNicknameException;
import me.yunchat.domain.user.exception.UserErrorCode;
import me.yunchat.domain.user.exception.NotSamePasswordException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NoUserNicknameException(UserErrorCode.NO_USER_NICKNAME));
    }

    public User save(SignUpRequestDto request) {
        if (request.getPassword().equals(request.getConfirmPassword())) {
            throw new NotSamePasswordException(UserErrorCode.NOT_SAME_PASSWORD);
        }

        return userRepository.save(User.builder()
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build());
    }
}
