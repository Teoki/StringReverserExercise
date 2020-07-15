package de.check24.teo.springproject.spring.project.core.externalinterfaces;

import de.check24.teo.springproject.spring.project.core.dtos.CardData;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;

import java.util.Optional;

public interface Database {
    Optional<CardData> getCardDataByCardId(CardId cardId);
}
