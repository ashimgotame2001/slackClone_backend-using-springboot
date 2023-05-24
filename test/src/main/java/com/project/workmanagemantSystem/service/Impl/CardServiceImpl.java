package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.BoardSection;
import com.project.workmanagemantSystem.domain.Card;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.enumeration.Status;
import com.project.workmanagemantSystem.domain.request.CardRequest;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.BoardSectionRepository;
import com.project.workmanagemantSystem.repository.CardRepository;
import com.project.workmanagemantSystem.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final BoardSectionRepository boardSectionRepository;
    private final CardRepository cardRepository;

    @Override
    public ApiResponse addCardsToSections(CardRequest cardRequest) {
        BoardSection section = boardSectionRepository.findById(cardRequest.getSectionCode())
                .orElseThrow(() -> new BadAlertException(
                        "section not found",
                        "Board",
                        "INVALID_SECTION_CODE"
                ));
        Card card = Card.builder()
                .id(UUID.randomUUID())
                .name(cardRequest.getCard().getName())
                .notes(cardRequest.getCard().getNotes())
                .priority(cardRequest.getCard().getPriority())
                .status(Status.ACTIVE.name())
                .build();
          cardRepository.save(card);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        section.setCards(cards);
        boardSectionRepository.save(section);
        return ApiResponse.builder().message("Card added").status(HttpStatus.CREATED).build();
    }
}
