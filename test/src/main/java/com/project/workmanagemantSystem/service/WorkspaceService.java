package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Channels;
import com.project.workmanagemantSystem.domain.Response;
import com.project.workmanagemantSystem.domain.WorkSpace;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    Response createNewWorkSpace(String name, UUID clientCode);

    List<Channels> getChannelsByWorkspace(UUID workSpaceCode);

    Response addChannelsToWorkSpace(UUID workSpaceCode, String name);

    WorkSpace getWorkspace(UUID workSpaceCode);
}
