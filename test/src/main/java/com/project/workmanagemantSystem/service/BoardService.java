package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Board;
import com.project.workmanagemantSystem.domain.Response;

import java.util.UUID;

public interface BoardService {

 Response createNewBoard(UUID channelCode);

 Board  getBoardByChannelCode(UUID channelCode);
}
