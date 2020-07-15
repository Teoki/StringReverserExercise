package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.*;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Service {

    private final Database database;

    public Service(Database database) {
        this.database = database;
    }

    public void runAction(CardId cardId, Pin pin, AtmAndMenus atmAndMenus, Optional<Amount> amount) {
        //TODO überprüfe welches Atm und welche Menüs gewählt worden sind
        //TODO überprüfe ob ein ENUM für Withdraw 50, 100, 500, Custom Amount ausgewählt wurde?? oder new Withdraw(new WithdrawFifty())

        final Optional<CardData> cardDataDB = database.getCardDataByCardId(cardId);
        cardDataDB.ifPresent(cardData -> {
            //Inputs werden überprüft (richtiger Pin und genügend Geld zum abheben)
            if (isInputCorrect(cardId, pin, amount, cardDataDB.get())) {
                System.out.println("Your requested amount will be submitted...");
                //TODO SUBMIT
            } else /*if (50/100/500)*/ {
                System.out.println("Please try again: (or: CANCELED)");
                //TODO erneute eingabe anfordern oder abbrechen, falls requested amount empty ist
            }
        });
    }

    private boolean isInputCorrect(CardId cardId, Pin pin, Optional<Amount> requestedAmount, CardData cardDataDB) {
        try {
            if (checkPin(pin, cardDataDB)) {
                return false;
            }
            if (cardDataDB.getCurrentAmount().amount < requestedAmount.get().amount) { //TODO hier muss noch refactored werden
                System.out.println("Not enough money! Your requested amount of money is: " + requestedAmount.get().amount + " | Your actual amount of money is: " + cardDataDB.getCurrentAmount().amount);
                return false;
            } else if (cardDataDB.getCurrentAmount().amount >= requestedAmount.get().amount && requestedAmount.get().amount >= 0) {
                System.out.println("All data correct");
                return true;
            }
        } catch (NoSuchElementException e) { //wenn kein amount angegeben wurde also requestedAmount ist empty und sein value ist null
            System.out.println("You didn't request a amount. Please Choose 50, 100, 500 or custom amount");
            return false;
        } catch (Exception e) {
            System.out.println("Incorrect input"); //wenn die cardId in der Datenbank nicht existiert
            return false;
        }
        return false;
    }

    private boolean checkPin(Pin pin, CardData cardDataDB) {
        if (cardDataDB.getPin().equals(pin)) {
            System.out.println("Correct pin");
        } else {
            System.out.println("Incorrect pin");
            return true;
        }
        return false;
    }

}
