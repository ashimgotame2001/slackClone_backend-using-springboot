package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Board;
import com.project.workmanagemantSystem.Responce.ApiResponse;

import java.util.UUID;

public interface BoardService {

 ApiResponse createNewBoard(UUID channelCode, String name);

 Board  getBoardByChannelCode(UUID channelCode);
}
