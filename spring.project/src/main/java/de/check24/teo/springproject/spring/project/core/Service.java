package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.*;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Service {

    private final Database database;
    private final Bank bank;


    public Service(Database database, Bank bank) {
        this.database = database;
        this.bank = bank;
    }

    public void runAction(CardId cardId, Pin pin, AtmAndMenus atmAndMenus, Optional<Amount> amount) {
        final Optional<CardData> cardDataDB = database.getCardDataByCardId(cardId);
        cardDataDB.ifPresent(cardData -> {
            //Inputs werden überprüft (richtiger Pin und genügend Geld zum abheben)
            if (isInputCorrect(cardId, pin, amount, cardDataDB.get())) {
                System.out.println("Your requested amount will be submitted...");
                bank
                        .getByAtmId(atmAndMenus.atmId).get()
                        .getByMenuButtonLabel(atmAndMenus.menuIds.get(0)).get() //TODO hier ist noch ein fehler, wrs lieber nur "atmAndMenus.menuIds" nehmen
                        .submit(atmAndMenus.menuIds.subList(1, atmAndMenus.menuIds.size()), cardId, amount);
                System.out.println(bank.getByAtmId(atmAndMenus.atmId));

            } else {
                System.out.println("Please try again: (or: CANCELED)");
                //TODO erneute eingabe anfordern oder abbrechen, falls requested amount empty ist
            }
        });
    }

    private boolean isInputCorrect(CardId cardId, Pin pin, Optional<Amount> requestedAmount, CardData cardDataDB) {
        try {
            if (!isCorrectPin(pin, cardDataDB)) {
                return false;
            }
            if (requestedAmount.isPresent()) {
                if (cardDataDB.getCurrentAmount().amount < requestedAmount.get().amount) {
                    System.out.println("Not enough money! Your requested amount of money is: " + requestedAmount.get().amount + " | Your actual amount of money is: " + cardDataDB.getCurrentAmount().amount);
                    return false;
                } else if (cardDataDB.getCurrentAmount().amount >= requestedAmount.get().amount && requestedAmount.get().amount >= 0) {
                    System.out.println("All data correct");
                    return true;
                }
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

    private boolean isCorrectPin(Pin pin, CardData cardDataDB) {
        if (cardDataDB.getPin().equals(pin)) {
            System.out.println("Correct pin");
            return true;
        } else {
            System.out.println("Incorrect pin");
            return false;
        }
    }

}
