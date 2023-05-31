package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.domain.Channels;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.WorkSpace;
import com.project.workmanagemantSystem.domain.request.WorkSpaceMemberRequest;
import com.project.workmanagemantSystem.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
@RequiredArgsConstructor
public class WorkspaceResource {
    private final WorkspaceService workspaceService;

    @PostMapping("/register/{name}/{clientCode}")
   ApiResponse registerWorkSpace(
            @PathVariable String name,
            @PathVariable UUID clientCode
            ){
       return workspaceService.createNewWorkSpace(name,clientCode);
    }

    @GetMapping
    List<WorkSpace> getAllWorkspaceByLoggedUser(){
        return workspaceService.getWorkSpaceOfLoggedUser();
    }

    @GetMapping("/channels/{workSpaceCode}")
    List<Channels> getAllWorkSpaceChannel(@PathVariable UUID workSpaceCode){
        return workspaceService.getChannelsByWorkspace(workSpaceCode);
    }

    @PostMapping("add/{name}/{workSpaceCode}")
    ApiResponse addChannelToWorkSpace(
            @PathVariable UUID workSpaceCode,
            @PathVariable String name){
        return workspaceService.addChannelsToWorkSpace(workSpaceCode,name);
    }

    @GetMapping("/get/workspace/{workSpaceCode}")
    ResponseEntity<WorkSpace> getWorkspaceByID(@PathVariable UUID workSpaceCode){
        return ResponseEntity.ok(workspaceService.getWorkspace(workSpaceCode));
    }

    @PostMapping("/add/member")
    ApiResponse addMemberInWorkSpace(@RequestBody WorkSpaceMemberRequest workSpaceMemberRequest){
      return workspaceService.addMemberToWorkSpace(workSpaceMemberRequest.getEmail(), workSpaceMemberRequest.getWorkspaceCode());
    }


}
