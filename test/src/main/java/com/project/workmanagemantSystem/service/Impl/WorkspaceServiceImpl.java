package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.*;
import com.project.workmanagemantSystem.repository.ChannelsRepository;
import com.project.workmanagemantSystem.repository.ClientRepository;
import com.project.workmanagemantSystem.repository.WorkspaceRepository;
import com.project.workmanagemantSystem.service.AuthenticationService;
import com.project.workmanagemantSystem.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final AuthenticationService service;
    private final WorkspaceRepository workspaceRepository;
    private final ClientRepository clientRepository;
    private final ChannelsRepository channelsRepository;

    @Override
    public Response createNewWorkSpace(String name, UUID clientCode) {
        Client client = clientRepository.findById(clientCode).orElseThrow();
        User user = service.getLoggedUserDetails();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Channels defaultChannelOne = Channels.builder()
                .id(UUID.randomUUID())
                .name("general")
                .members(userList)
                .build();
        Channels defaultChannelTwo = Channels.builder()
                .id(UUID.randomUUID())
                .name(name)
                .members(userList)
                .build();
        channelsRepository.save(defaultChannelOne);
        channelsRepository.save(defaultChannelTwo);
        List<Channels> channelsList = new ArrayList<>();
        channelsList.add(defaultChannelOne);
        channelsList.add(defaultChannelTwo);
        WorkSpace workSpace = WorkSpace
                .builder()
                .id(UUID.randomUUID())
                .name(name)
                .Channels(channelsList)
                .members(userList)
                .clientCode(client)
                .build();
        workspaceRepository.save(workSpace);
        return Response.builder().message("Registered").status(HttpStatus.CREATED).build();
    }

    @Override
    public List<Channels> getChannelsByWorkspace(UUID workSpaceCode) {
        WorkSpace workSpace = workspaceRepository.findById(workSpaceCode).orElseThrow();
        List<Channels> channelsList = workSpace.getChannels();
        return channelsList;
    }

    @Override
    public Response addChannelsToWorkSpace(UUID workSpaceCode, String name) {
        User user = service.getLoggedUserDetails();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Channels channels = Channels.builder()
                .id(UUID.randomUUID())
                .name(name)
                .members(userList)
                .build();
        channelsRepository.save(channels);
        WorkSpace workSpace = workspaceRepository.findById(workSpaceCode).orElseThrow();
        List<Channels> channelsList = workSpace.getChannels();
        channelsList.add(channels);
        workSpace.setChannels(channelsList);
        workspaceRepository.save(workSpace);
        return Response.builder().message("Channel added").status(HttpStatus.CREATED).build();
    }

    @Override
    public WorkSpace getWorkspace(UUID workSpaceCode) {
        WorkSpace workSpace = workspaceRepository.findById(workSpaceCode).orElseThrow();
        return workSpace;
    }
}
