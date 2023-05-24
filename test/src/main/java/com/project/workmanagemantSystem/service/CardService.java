package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.request.CardRequest;

public interface CardService {

  ApiResponse addCardsToSections(CardRequest cardRequest);
}
