package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Messages;

import java.util.UUID;

public interface MessageService {

    Messages getUserContent(Messages messages, UUID senderCode,UUID ReceiverCode);

    Messages getChannelContent(Messages messages,UUID channelCode);
}
