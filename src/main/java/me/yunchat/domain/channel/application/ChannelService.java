package me.yunchat.domain.channel.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.dao.ChannelRepository;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.channel.dto.ChannelInOutDto;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

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

    public List<User> getNowUsers(String channelName) {
        return findByChannelName(channelName).getNowUsers();
    }

    public void enterChannel(ChannelInOutDto channelInOutDto) {
        Channel channel = channelInOutDto.getChannel();
        User user = channelInOutDto.getUser();

        channel.getNowUsers().add(user);
        user.setNowChannel(channel);

        channelRepository.save(channel);
        userRepository.save(user);
    }

    public void exitChannel(ChannelInOutDto channelInOutDto) {
        Channel channel = channelInOutDto.getChannel();
        User user = channelInOutDto.getUser();

        channel.getNowUsers().remove(user);
        user.setNowChannel(null);

        channelRepository.save(channel);
        userRepository.save(user);
    }

    public void updateLastChatTime(String channelName, LocalDateTime lastChatTime) {
        Channel channel = findByChannelName(channelName);
        channel.updateLastChatTime(lastChatTime);
        channelRepository.save(channel);
    }
}
