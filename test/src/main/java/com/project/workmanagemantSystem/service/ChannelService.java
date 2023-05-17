package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Response;

import java.util.UUID;

public interface ChannelService {

 Response removeChannelFromWorkSpace(UUID workspaceCode, UUID channelCode);

 Response addExistingMembersToChannels(UUID usercode,UUID channelcode);

 Response createBoardForChannel(UUID channelCode);
}
