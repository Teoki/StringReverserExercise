package de.check24.teo.springproject.spring.project.extern.out.database.mydatabase;

import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.Map;

public class MyDatabaseImpl implements Database {

    public MyDatabaseImpl() {
        cardList.put("1234567", new CardData("0987", 1000));
        cardList.put("1122334", new CardData("1122", 500));
        cardList.put("0302010", new CardData("0000", 250));
        cardList.put("7897897", new CardData("7897", 125));
        cardList.put("9876543", new CardData("9876", 0));
    }

    //         cardID, pin&currentAmount
    public Map<String, CardData> cardList; //String zu Interger machen?

    //Test
    public String checkCardWithPin(String cardId, String pin) {
        if (cardList.get(cardId) == null) {
            return "Wrong cardId"; //Weil keine pin/amount gefunden wurde
        }
        if (cardList.get(cardId).getPin().equals(pin)) {
            return "Correct pin";
        }
        return "UserInput, pin and current amount is incorrect";
    }

}
