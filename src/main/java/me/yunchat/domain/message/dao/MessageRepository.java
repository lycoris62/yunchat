package me.yunchat.domain.message.dao;

import me.yunchat.domain.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
