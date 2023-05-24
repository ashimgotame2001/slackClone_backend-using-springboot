package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.domain.BoardSection;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.request.BoardSectionRequest;
import com.project.workmanagemantSystem.service.BoardSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/section")
public class SectionResource {
    private final BoardSectionService boardSectionService;

    @PostMapping("/add")
    ApiResponse addBoardSections(@RequestBody BoardSectionRequest boardSectionRequest){
        return boardSectionService.addSectionToBoard(boardSectionRequest);
    }

    @GetMapping("/{sectionCode}")
    BoardSection getSectionByCode(@PathVariable UUID sectionCode){
        return boardSectionService.getSectionDetail(sectionCode);
    }
}
