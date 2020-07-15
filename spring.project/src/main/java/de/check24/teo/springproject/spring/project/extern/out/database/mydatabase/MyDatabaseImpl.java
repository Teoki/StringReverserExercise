package de.check24.teo.springproject.spring.project.extern.out.database.mydatabase;

import de.check24.teo.springproject.spring.project.core.dtos.CardData;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MyDatabaseImpl implements Database {

    private final Map<CardId, CardDataDao> cardList;

    public MyDatabaseImpl() {
        cardList = new HashMap<>();
        int otherData = 1;
        cardList.put(new CardId("1234567"), new CardDataDao("0987", 1000, otherData, new CardId("1234567")));
        cardList.put(new CardId("1122334"), new CardDataDao("1122", 500, otherData, new CardId("1122334")));
        cardList.put(new CardId("0302010"), new CardDataDao("0000", 250, otherData, new CardId("0302010")));
        cardList.put(new CardId("7897897"), new CardDataDao("7897", 125, otherData, new CardId("7897897")));
        cardList.put(new CardId("9876543"), new CardDataDao("9876", 0, otherData, new CardId("9876543")));
    }

    @Override
    public Optional<CardData> getCardDataByCardId(CardId cardId) {
        try {
            return Optional.of(cardList.get(cardId).toDto());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
