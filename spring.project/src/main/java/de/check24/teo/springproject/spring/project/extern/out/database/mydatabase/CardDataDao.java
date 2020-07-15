package de.check24.teo.springproject.spring.project.extern.out.database.mydatabase;

import de.check24.teo.springproject.spring.project.core.dtos.CardData;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;

public class CardDataDao { // For DB only! (Dao = Data access object)

    private String pin;
    private int currentAmount;
    private int otherData;
    private CardId cardId;

    public CardDataDao(String pin, int currentAmount, int otherData, CardId cardId) {
        this.pin = pin;
        this.currentAmount = currentAmount;
        this.otherData = otherData;
        this.cardId = cardId;
    }

    public String getPin() {
        return pin;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public CardData toDto() {
        return new CardData(pin, currentAmount);
    }
}
