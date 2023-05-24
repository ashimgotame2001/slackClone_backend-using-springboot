package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.Board;
import com.project.workmanagemantSystem.domain.Channels;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.BoardRepository;
import com.project.workmanagemantSystem.repository.ChannelsRepository;
import com.project.workmanagemantSystem.service.AuthenticationService;
import com.project.workmanagemantSystem.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final AuthenticationService service;
    private final ChannelsRepository channelsRepository;
    private final BoardRepository boardRepository;
    @Override
    public ApiResponse createNewBoard(UUID channelCode, String name) {
        Channels channels = channelsRepository.findById(channelCode)
                .orElseThrow(()-> new BadAlertException(
                        "Channel not found",
                        "channel",
                        "INVALID_CHANNEL_CODE"
                ));
        User user = service.getLoggedUserDetails();
        Board board = new Board();
        board.setId(UUID.randomUUID());
        board.setName(name);
        board.setCreatedAt(LocalDateTime.now());
        board.setCreatedBy(user);
        board.setChannel(channels);
        boardRepository.save(board);
        return ApiResponse.builder().message("Board created").status(HttpStatus.CREATED).build();
    }

    @Override
    public Board getBoardByChannelCode(UUID channelCode) {
        Board board = boardRepository.findByChannelId(channelCode)
                .orElseThrow(()-> new BadAlertException(
                        "board not found",
                        "Board",
                        "INVALID_BOARD_CODE"
                ));
        return board;
    }
}
