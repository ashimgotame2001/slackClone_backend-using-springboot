package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.*;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.*;
import com.project.workmanagemantSystem.service.AuthenticationService;
import com.project.workmanagemantSystem.service.ChannelService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final AuthenticationService service;
    private final ChannelsRepository channelsRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public Response removeChannelFromWorkSpace(UUID workspaceCode, UUID channelCode) {
        return null;
    }

    @Override
    public Response addExistingMembersToChannels(UUID userCode,UUID channelCode) {
        User user  = userRepository.findById(userCode).orElseThrow(()-> new BadAlertException(
                "user not found",
                "Users",
                "INVALID_USER_ID"
        ));
        Members members = new Members();
        members.setId(UUID.randomUUID());
        members.setEmail(user.getEmail());
        members.setFirstName(user.getFirstName());
        members.setLastName(user.getLastName());
        members.setPhone(user.getPhone());
        memberRepository.save(members);
        Channels channels = channelsRepository.findById(channelCode).orElseThrow(()-> new BadAlertException(
                "channel not found",
                "Channels",
                "INVALID_CHANNEL_ID"
        ));
        List<Members> userList = channels.getMembers();
        userList.add(members);
        channelsRepository.save(channels);
        return Response.builder().message("Member added").status(HttpStatus.OK).build();
    }

    @Override
    public Response createBoardForChannel(UUID channelCode) {
        User user = service.getLoggedUserDetails();
        Channels channels = channelsRepository.findById(channelCode).orElseThrow(()-> new BadAlertException(
                "Channel ID not found",
                "Channels",
                "INVALID_CHANNEL_ID"
        ));
        Board board = new Board();
        board.setId(UUID.randomUUID());
        board.setCreatedAt(LocalDateTime.now());
        board.setCreatedBy(user);
        board.setChannel(channels);
        boardRepository.save(board);
        return Response.builder().message("Board Created successfully").status(HttpStatus.CREATED).build();
    }
}
