package me.yunchat.domain.message.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.message.dao.MessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;


}
