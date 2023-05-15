package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.domain.Response;
import com.project.workmanagemantSystem.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/channel")
public class ChannelResource {

    private final ChannelService channelService;



    @DeleteMapping("/remove/{channelCode}/{workSpaceCode}")
    Response removeChannel(
            @PathVariable UUID channelCode,
            @PathVariable UUID workSpaceCode
    ){
        return null;
    }
}
