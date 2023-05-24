package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.Responce.ApiResponse;

import java.util.UUID;

public interface ChannelService {

 ApiResponse removeChannelFromWorkSpace(UUID workspaceCode, UUID channelCode);

 ApiResponse addExistingMembersToChannels(UUID usercode, UUID channelcode);

 ApiResponse createBoardForChannel(UUID channelCode);
}
