package me.yunchat.global.config.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import me.yunchat.global.config.security.loginAuth.LoginUserDetails;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(a -> a.requestMatchers(PathRequest.toH2Console()).permitAll());
        http.authorizeHttpRequests(a -> a.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll());
        http.authorizeHttpRequests(a -> a.requestMatchers("/signup", "/login").permitAll());
        http.authorizeHttpRequests(a -> a.anyRequest().hasRole("USER"));

        http.formLogin(getFormLoginConfigurerCustomizer());
        http.logout(getLogoutConfigurerCustomizer());

        return http.build();
    }

    private Customizer<FormLoginConfigurer<HttpSecurity>> getFormLoginConfigurerCustomizer() {
        return form -> {
            form.loginPage("/login");
            form.loginProcessingUrl("/loginProc");
            form.usernameParameter("nickname");
            form.passwordParameter("password");
            form.successHandler(getAuthenticationSuccessHandler());
        };
    }

    private Customizer<LogoutConfigurer<HttpSecurity>> getLogoutConfigurerCustomizer() {
        return logout -> {
            logout.logoutSuccessUrl("/login");
            logout.invalidateHttpSession(true);
        };
    }

    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("login success : {}", authentication.getName());
            LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
            User loginUser = loginUserDetails.getUser();
            updateUserLoginTime(loginUser);
            response.sendRedirect("/profile/" + loginUser.getNickname());
        };
    }

    private void updateUserLoginTime(User user) {
        User findUser = userRepository.findByNickname(user.getNickname()).get();
        findUser.updateLastLoginTime(LocalDateTime.now());
        userRepository.save(findUser);
    }
}
