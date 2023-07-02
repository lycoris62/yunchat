package me.yunchat.domain.user.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import me.yunchat.domain.user.dto.UserSaveDto;
import me.yunchat.domain.user.exception.NoUserNicknameException;
import me.yunchat.domain.user.exception.UserErrorCode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NoUserNicknameException(UserErrorCode.NO_USER_NICKNAME));
    }

    public User save(UserSaveDto userSaveDto) {

        return userRepository.save(User.builder()
                .nickname(userSaveDto.getNickname())
                .password(userSaveDto.getPassword())
                .build());
    }
}
