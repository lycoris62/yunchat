package me.yunchat.domain.channel.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.dao.ChannelRepository;
import me.yunchat.domain.channel.domain.Channel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public boolean existsByChannelName(String channelName) {
        return channelRepository.existsByChannelName(channelName);
    }

    public Channel createChannel(String channelName) {
        return channelRepository.save(Channel.builder()
                .channelName(channelName)
                .build());
    }

    public Channel findByChannelName(String channelName) {
        return channelRepository.findByChannelName(channelName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채널입니다."));
    }

    public List<Channel> findAll() {
        return channelRepository.findAll();
    }
}
