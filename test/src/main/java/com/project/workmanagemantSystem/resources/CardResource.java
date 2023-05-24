package com.project.workmanagemantSystem.resources;

import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.request.CardRequest;
import com.project.workmanagemantSystem.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/card")
public class CardResource {

  private final CardService cardService;


  @PostMapping
    ApiResponse addCardToSection(@RequestBody CardRequest request) {
      return cardService.addCardsToSections(request);
    }
}
