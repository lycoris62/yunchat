package me.yunchat.domain.channel.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yunchat.domain.model.BaseEntity;
import me.yunchat.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id", nullable = false)
    private Long id;

    @Column(name = "channel_name", nullable = false, unique = true)
    private String channelName;

    @OneToMany(mappedBy = "nowChannel")
    @Column(name = "now_users")
    private List<User> nowUsers;

    @Column(name = "last_chat_time")
    private LocalDateTime lastChatTime;

    public void updateLastChatTime(LocalDateTime lastChatTime) {
        this.lastChatTime = lastChatTime;
    }

    @Builder
    public Channel(String channelName) {
        this.channelName = channelName;
    }
}
