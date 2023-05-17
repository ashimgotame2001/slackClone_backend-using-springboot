package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.domain.Response;
import com.project.workmanagemantSystem.service.ChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/channel")
public class ChannelResource {

    private final ChannelService channelService;
//    private final
    @PostMapping("/add-member/{channelCode/{memberCode}")
    ResponseEntity<Response> addWorkSpaceMemberToChannel(
            @PathVariable UUID channelCode,
            @PathVariable UUID memberCode
    ){
        return ResponseEntity.ok(channelService.addExistingMembersToChannels(memberCode,channelCode));
    }

    @PostMapping("/create-board/{channelCode}")
    ResponseEntity<Response> createBoardForChannel(@PathVariable UUID channelCode){
        return ResponseEntity.ok(channelService.createBoardForChannel(channelCode));
    }
    @DeleteMapping("/remove/{channelCode}/{workSpaceCode}")
    Response removeChannel(
            @PathVariable UUID channelCode,
            @PathVariable UUID workSpaceCode
    ){
        return null;
    }
}
