package me.yunchat.global.config.security.loginAuth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public LoginUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found : " + username));

        return new LoginUserDetails(user, "ROLE_USER");
    }
}
