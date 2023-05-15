package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.Channels;
import com.project.workmanagemantSystem.domain.Response;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.domain.WorkSpace;
import com.project.workmanagemantSystem.repository.ChannelsRepository;
import com.project.workmanagemantSystem.repository.UserRepository;
import com.project.workmanagemantSystem.repository.WorkspaceRepository;
import com.project.workmanagemantSystem.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final WorkspaceRepository workspaceRepository;
    private final ChannelsRepository channelsRepository;
    private final UserRepository userRepository;


    @Override
    public Response removeChannelFromWorkSpace(UUID workspaceCode, UUID channelCode) {
        return null;
    }

    @Override
    public Response addExistingMembersToChannels(UUID userId,UUID channelId) {
        User user  = userRepository.findById(userId).orElseThrow();
        Channels channels = channelsRepository.findById(channelId).orElseThrow();
        List<User> userList = channels.getMembers();
        userList.add(user);
        channelsRepository.save(channels);
        return Response.builder().message("Member added").status(HttpStatus.OK).build();
    }
}
