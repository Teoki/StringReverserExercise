package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.*;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component //ist und macht das selbe wie @org.springframework.stereotype.Service. (Das ist nur spezifischer)
public class Service {

    private static Logger LOG = LoggerFactory.getLogger(Service.class);
    private final Database database;
    private final Bank bank;

    @Autowired
    public Service(Database database, Bank bank) {
        this.database = database;
        this.bank = bank;
    }

    public void runAction(CardId cardId, Pin pin, AtmAndMenus atmAndMenus, Optional<Amount> amount) {
        database.getCardDataByCardId(cardId).ifPresent(cardData -> {
            //Inputs werden überprüft (richtiger Pin und genügend Geld zum abheben)
            if (isInputCorrect(cardId, pin, amount, cardData)) {
                LOG.info("Your requested amount will be submitted...");
                bank
                        .getByAtmId(atmAndMenus.atmId).get()
                        .getByMenuButtonId(atmAndMenus.menuIds.get(0)).get()
                        .submit(atmAndMenus.menuIds.subList(1, atmAndMenus.menuIds.size()), cardId, amount);
                LOG.info("id: {}", bank.getByAtmId(atmAndMenus.atmId).get().id); //für jeden weiteren Parameter immer ein {} dazu z.B. LOG.info("id: {}{}{}", object1, object2, object3)
            } else {
                //erneute Eingabe anfordern oder abbrechen, falls requested amount empty ist
                LOG.info("Please try again: (or: CANCELED)");
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
                    LOG.info("Not enough money! Your requested amount of money is: " + requestedAmount.get().amount + " | Your actual amount of money is: " + cardDataDB.getCurrentAmount().amount);
                    return false;
                } else if (cardDataDB.getCurrentAmount().amount >= requestedAmount.get().amount && requestedAmount.get().amount >= 0) {
                    LOG.info("All data correct");
                    return true;
                }
            }
        } catch (NoSuchElementException e) { //wenn kein amount angegeben wurde also requestedAmount ist empty und sein value ist null
            LOG.info("You didn't request a amount. Please Choose 50, 100, 500 or custom amount");
        } catch (Exception e) {
            LOG.info("Incorrect input"); //wenn die cardId in der Datenbank nicht existiert
        }
        return false;
    }

    private boolean isCorrectPin(Pin pin, CardData cardDataDB) {
        if (cardDataDB.getPin().equals(pin)) {
            LOG.info("Correct pin");
            return true;
        } else {
            LOG.info("Incorrect pin");
            return false;
        }
    }

}
