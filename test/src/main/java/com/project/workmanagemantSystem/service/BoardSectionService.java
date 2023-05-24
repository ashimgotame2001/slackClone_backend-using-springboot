package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.BoardSection;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.request.BoardSectionRequest;

import java.util.UUID;

public interface BoardSectionService {
    ApiResponse addSectionToBoard(BoardSectionRequest request);

    BoardSection getSectionDetail(UUID sectionCode);

}
