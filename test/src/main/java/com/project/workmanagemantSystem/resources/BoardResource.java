package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.domain.Board;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/board")
public class BoardResource {

    private final BoardService boardService;

    @PostMapping("/create/{channelCode}/{name}")
    ApiResponse addBoardToChannel(@PathVariable UUID channelCode, @PathVariable String name ){
        return boardService.createNewBoard(channelCode,name);
    }

    @GetMapping("/{channelCode}")
    Board getBoardByChannelCode(@PathVariable UUID channelCode){
        return  boardService.getBoardByChannelCode(channelCode);
    }

}
