package me.yunchat.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.model.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "now_channel")
    private Channel nowChannel;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    public void updateLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setNowChannel(Channel channel) {
        this.nowChannel = channel;
    }

    @Builder
    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        this.lastLoginTime = LocalDateTime.now();
    }
}
