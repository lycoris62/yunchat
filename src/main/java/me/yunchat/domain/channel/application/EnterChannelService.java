package me.yunchat.domain.channel.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.dao.ChannelRepository;
import me.yunchat.domain.channel.domain.Channel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterChannelService {

    private final ChannelRepository channelRepository;

    public String enter(String channelName) {
        Channel channel = channelRepository.findByChannelName(channelName)
                .orElseGet(() -> channelRepository.save(new Channel(channelName)));
        return channel.getChannelName();
    }
}
