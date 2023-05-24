package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.Messages;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.MemberRepository;
import com.project.workmanagemantSystem.repository.MessageRepository;
import com.project.workmanagemantSystem.repository.UserRepository;
import com.project.workmanagemantSystem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public Messages getUserContent(Messages messages, UUID senderCode, UUID ReceiverCode) {
        try {
            User senderDetail = userRepository.findById(senderCode)
                    .orElseThrow(() -> new BadAlertException(
                            "user not found",
                            "users",
                            "INVALID_USER_CODE"
                    ));
            User receiverDetail = userRepository.findById(ReceiverCode)
                    .orElseThrow(() -> new BadAlertException(
                            "user not found",
                            "users",
                            "INVALID_USER_CODE"
                    ));
            Messages addMessage = new Messages();
            addMessage.setId(UUID.randomUUID());
            addMessage.setMessage(messages.getMessage());
            addMessage.setIsBookmarked(messages.getIsBookmarked());
            addMessage.setSenderId(senderDetail.getId());
            addMessage.setReceiverId(receiverDetail.getId());
            Thread.sleep(2000);
           return messageRepository.save(addMessage);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Messages getChannelContent(Messages messages, UUID channelCode) {
        return null;
    }
}
