package me.yunchat.domain.channel.dao;

import me.yunchat.domain.channel.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findByChannelName(String channelName);
    boolean existsByChannelName(String channelName);
}
