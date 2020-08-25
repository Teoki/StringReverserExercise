package de.check24.teo.springproject.spring.project.extern.out.database.h2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //zeigt an, dass es eine JPA Entity ist
public class CardDataEntity { // For DB only! (Dao = Data access object)

    private String pin;
    private int currentAmount;
    private int otherData;
    @Id
    @Column(unique = true) //CardId muss einzigartig sein in allen CardDataEntity's
    private String cardId;

    public CardDataEntity() {
    }

    public CardDataEntity(String pin, int currentAmount, int otherData, String cardId) {
        this.pin = pin;
        this.currentAmount = currentAmount;
        this.otherData = otherData;
        this.cardId = cardId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getOtherData() {
        return otherData;
    }

    public void setOtherData(int otherData) {
        this.otherData = otherData;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public de.check24.teo.springproject.spring.project.core.dtos.CardData toDto() {
        return new de.check24.teo.springproject.spring.project.core.dtos.CardData(pin, currentAmount);
    }
}
