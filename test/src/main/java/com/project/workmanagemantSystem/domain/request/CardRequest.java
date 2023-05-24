package com.project.workmanagemantSystem.domain.request;

import com.project.workmanagemantSystem.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardRequest {
    private UUID sectionCode;
    private Card card;
}
