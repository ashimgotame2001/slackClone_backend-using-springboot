package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.*;
import com.project.workmanagemantSystem.domain.enumeration.Status;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.*;
import com.project.workmanagemantSystem.service.AuthenticationService;
import com.project.workmanagemantSystem.service.WorkspaceService;
import com.project.workmanagemantSystem.utils.RandomCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final AuthenticationService service;
    private final WorkspaceRepository workspaceRepository;
    private final ClientRepository clientRepository;
    private final ChannelsRepository channelsRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public ApiResponse createNewWorkSpace(String name, UUID clientCode) {
        Client client = clientRepository.findById(clientCode).orElseThrow();
        User user = service.getLoggedUserDetails();
        Members members = new Members();
        members.setId(UUID.randomUUID());
        members.setEmail(user.getEmail());
        members.setFirstName(user.getFirstName());
        members.setLastName(user.getLastName());
        members.setPhone(user.getPhone());
        memberRepository.save(members);
        List<Members> userList = new ArrayList<>();
        userList.add(members);
        Channels defaultChannelOne = Channels.builder()
                .id(UUID.randomUUID())
                .name("general")
                .channelType("PUBLIC")
                .members(userList)
                .build();
        Channels defaultChannelTwo = Channels.builder()
                .id(UUID.randomUUID())
                .name(name)
                .channelType("PUBLIC")
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
                .startedAt(LocalDateTime.now())
                .build();
        workspaceRepository.save(workSpace);
        return ApiResponse.builder().message("Registered").status(HttpStatus.CREATED).build();
    }

    @Override
    public List<Channels> getChannelsByWorkspace(UUID workSpaceCode) {
        WorkSpace workSpace = workspaceRepository.findById(workSpaceCode).orElseThrow();
        List<Channels> channelsList = workSpace.getChannels();
        return channelsList;
    }

    @Override
    public ApiResponse addChannelsToWorkSpace(UUID workSpaceCode, String name) {
        User user = service.getLoggedUserDetails();

        Members members = new Members();
        members.setId(UUID.randomUUID());
        members.setEmail(user.getEmail());
        members.setFirstName(user.getFirstName());
        members.setLastName(user.getLastName());
        members.setPhone(user.getPhone());
        memberRepository.save(members);
        List<Members> userList = new ArrayList<>();
        userList.add(members);
        Channels channels = Channels.builder()
                .id(UUID.randomUUID())
                .name(name)
                .members(userList)
                .channelType("PRIVATE")
                .status(Status.ACTIVE.name())
                .build();
        channelsRepository.save(channels);
        WorkSpace workSpace = workspaceRepository.findById(workSpaceCode).orElseThrow();
        List<Channels> channelsList = workSpace.getChannels();
        channelsList.add(channels);
        workSpace.setChannels(channelsList);
        workspaceRepository.save(workSpace);
        return ApiResponse.builder().message("Channel added").status(HttpStatus.CREATED).build();
    }

    @Override
    public WorkSpace getWorkspace(UUID workSpaceCode) {
        WorkSpace workSpace = workspaceRepository.findById(workSpaceCode).orElseThrow(()->new BadAlertException(
                "WorkSpace Id not found",
                "WorkSpace Not Found",
                "INVALID_WORKSPACE_ID"
        ));
        return workSpace;
    }

    @Override
    public ApiResponse addMemberToWorkSpace(String email, UUID workspaceCode) {
        WorkSpace workSpace = workspaceRepository.findById(workspaceCode)
                .orElseThrow(()-> new BadAlertException(
                        "workspace not found ",
                        "WorkSpace",
                        "INVALID_WORKSPACE_CODE"
                ));
        Optional<User> u = userRepository.findByEmail(email);
        if(u.isEmpty()) {
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(RandomCodeGenerator.generateRandomCode()));
            user.setStatus(Status.PENDING.name());
            userRepository.save(user);

//            send email to user

            Members members = new Members();
            members.setId(UUID.randomUUID());
            members.setEmail(email);
            memberRepository.save(members);
            List<Members> membersList = workSpace.getMembers();
            membersList.add(members);
            workspaceRepository.save(workSpace);
            return ApiResponse.builder()
                    .message("Member Added")
                    .status(HttpStatus.OK)
                    .build();
        }else {
            throw new BadAlertException(
                    "Email already exists",
                    "User",
                    "INVALID_EMAIL"
            );
        }
    }

    @Override
    public List<WorkSpace> getWorkSpaceOfLoggedUser() {
        User user = service.getLoggedUserDetails();
        Client client = clientRepository.findByEmail(user.getEmail()).get();
        List< WorkSpace> workSpace = workspaceRepository.findByClientCode_Id(client.getId());
        return workSpace;
    }
}
