package de.check24.teo.springproject.spring.project.core.externalinterfaces;

import de.check24.teo.springproject.spring.project.core.dtos.CardData;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;

public interface Database {

    CardData getCardDataByCardId(CardId cardId);
}
