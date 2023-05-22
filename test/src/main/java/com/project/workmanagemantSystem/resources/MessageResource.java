package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.domain.Messages;
import com.project.workmanagemantSystem.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MessageResource {

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/user/message")
    @SendTo("/topic/{senderCode}/{receiverCode}")
    public Messages getUserMessage(
            @RequestBody Messages messages,
            @PathVariable UUID senderCode,
            @PathVariable UUID receiverCode
    ){
        return  messageService.getUserContent(messages,senderCode,receiverCode );
    }

    @MessageMapping("/channel/message")
    @SendTo("/topic/{channelCode}")
    public Messages getChannelMessage(
            @RequestBody Messages messages,
            @PathVariable UUID channelCode
    ){
        return  messageService.getChannelContent(messages,channelCode );
    }
}
