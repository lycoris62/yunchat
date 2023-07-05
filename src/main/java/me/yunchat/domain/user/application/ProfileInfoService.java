package me.yunchat.domain.user.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import me.yunchat.domain.user.dto.ProfileInfoDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileInfoService {

    private final UserRepository userRepository;

    public ProfileInfoDto getUserProfile(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저 닉네임"));

        return new ProfileInfoDto(user.getNickname(), user.getLastLoginTime());
    }
}
