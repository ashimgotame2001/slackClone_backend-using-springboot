package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.Board;
import com.project.workmanagemantSystem.domain.BoardSection;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.request.BoardSectionRequest;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.BoardRepository;
import com.project.workmanagemantSystem.repository.BoardSectionRepository;
import com.project.workmanagemantSystem.service.BoardSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardSectionServiceImpl implements BoardSectionService {

    private final BoardRepository boardRepository;
    private final BoardSectionRepository boardSectionRepository;

    @Override
    public ApiResponse addSectionToBoard(BoardSectionRequest request) {
        Board board = boardRepository.findById(request.getBoardCode())
                .orElseThrow(()-> new BadAlertException(
                        "board not found",
                        "Board",
                        "INVALID_BOARD_CODE"
                ));
        BoardSection section = BoardSection.builder()
                .id(UUID.randomUUID())
                .name(request.getSection().getName())
                .numberOfCards(0L)
                .build();
          boardSectionRepository.save(section);
        List<BoardSection> sections = new ArrayList<>();
        sections.add(section);
        board.setSections(sections);
        boardRepository.save(board);
        return ApiResponse.builder().message("Section added").status(HttpStatus.CREATED).build();
    }

    @Override
    public BoardSection getSectionDetail(UUID sectionCode) {
        BoardSection section = boardSectionRepository.findById(sectionCode).orElseThrow(()-> new BadAlertException(
                "section not found",
                "BoardSection",
                "INVALID_SECTION_CODE"
        ));
        return section;
    }
}
