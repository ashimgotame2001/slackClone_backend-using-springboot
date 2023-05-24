package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Channels;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.WorkSpace;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    ApiResponse createNewWorkSpace(String name, UUID clientCode);

    List<Channels> getChannelsByWorkspace(UUID workSpaceCode);

    ApiResponse addChannelsToWorkSpace(UUID workSpaceCode, String name);

    WorkSpace getWorkspace(UUID workSpaceCode);

    ApiResponse addMemberToWorkSpace(String email, UUID workspaceCode);
}
