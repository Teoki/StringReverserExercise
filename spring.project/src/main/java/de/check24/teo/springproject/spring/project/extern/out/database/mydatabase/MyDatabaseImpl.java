package de.check24.teo.springproject.spring.project.extern.out.database.mydatabase;

import de.check24.teo.springproject.spring.project.core.dtos.CardData;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.HashMap;
import java.util.Map;

public class MyDatabaseImpl implements Database {

    //                cardID, pin&currentAmount
    private final Map<String, CardDataDao> cardList;

    public MyDatabaseImpl() {
        cardList = new HashMap<>();
        int otherData = 1;
        cardList.put("1234567", new CardDataDao("0987", 1000, otherData));
        cardList.put("1122334", new CardDataDao("1122", 500, otherData));
        cardList.put("0302010", new CardDataDao("0000", 250, otherData));
        cardList.put("7897897", new CardDataDao("7897", 125, otherData));
        cardList.put("9876543", new CardDataDao("9876", 0, otherData));
    }


    @Override
    public CardData getCardDataByCardId(CardId cardId) {
        return null;
    }
}
