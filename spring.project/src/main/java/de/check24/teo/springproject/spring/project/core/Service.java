package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.*;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.Optional;

public class Service {

    private final Database database;

    public Service(Database database) {
        this.database = database;
    }

    public void runAction(CardId cardId, Pin pin, AtmAndMenus atmAndMenus, Optional<Amount> amount) {
        //TODO überprüfe ob ein ENUM für Withdraw 50, 100, 500, Custom Amount ausgewählt wurde??

        final CardData cardData = database.getCardDataByCardId(cardId);
        //TODO überprüfe ob cardId, pin und amount
        if (isCardIdPinAndAmountCorrect(cardId, pin, amount, cardData)) { //keine methode in database, geht aber auch nicht weil man sonst kein Zugriff auf die liste mit daten hat
            //TODO SUBMIT
        } else /*if (50/100/500)*/ {
            System.out.println("Wrong Input");
            //TODO erneute eingabe anfordern oder abbrechen
        }
    }

    private boolean isCardIdPinAndAmountCorrect(CardId cardId, Pin pin, Optional<Amount> requestedAmount, CardData cardData) {
        try {
            if (cardData.getPin().equals(pin)) {
                System.out.println("Correct pin");
            } else {
                System.out.println("Incorrect pin");
                return false;
            }

            if (cardData.getCurrentAmount().amount < requestedAmount.get().amount) {
                System.out.println("Not enough money! Your requested amount is: " + requestedAmount + " | Your actual amount is: " + cardData.getCurrentAmount().amount);
                return false;
            } else if (cardData.getCurrentAmount().amount > requestedAmount.get().amount && requestedAmount.get().amount >= 0) {
                System.out.println("You can have the requested amount of money!");
                return true;
            }
        } catch (Exception e) { //wenn die cardId nicht existiert (in der Datenbank)
            System.out.println("Incorrect input");
            return false;
        }
        return false;
    }

}
