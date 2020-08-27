package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.*;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service //ist und macht das selbe wie @Component (Service nur spezifischer)
public class Service {

    private static Logger LOG = LoggerFactory.getLogger(Service.class);
    private final Database database;
    private final Bank bank;

    @Autowired
    public Service(Database database, Bank bank) {
        this.database = database;
        this.bank = bank;
    }

    public Either<String, Integer> runAction(CardId cardId, Pin pin, AtmAndMenus atmAndMenus, Optional<Amount> amount) {
        return Option.ofOptional(database.getCardDataByCardId(cardId))
                .toEither(() -> "TEST in Service runAction()")
                .flatMap(cardData -> {
                            //Inputs werden überprüft (richtiger Pin und genügend Geld zum abheben)
                            return Try.run(() -> isInputCorrect(cardId, pin, amount, cardData)) //wie try catch
                                    .toEither() //wird zu einem Either umgewandelt mit Throwables bei left und void bei right
                                    .mapLeft(Throwable::getMessage) //falls elemente von left existieren (also Throwables), dann rufe getMessage von der Exception auf
                                    .flatMap(v -> { //falls Elemente im left exisitieren (falls es Exceptions gab) mach bei right nichts. Falls es kein left gibt dann mache was hier im right steht
                                        return Try.of(() ->
                                                bank
                                                        .getByAtmId(atmAndMenus.atmId).get()
                                                        .getByMenuButtonId(atmAndMenus.menuIds.get(0)).get()
                                                        .submit(atmAndMenus.menuIds.subList(1, atmAndMenus.menuIds.size()), cardId, amount)
                                        )
                                                .toEither()
                                                .mapLeft(Throwable::getMessage);
                                    });
                        }
                );
    }

    private void isInputCorrect(CardId cardId, Pin pin, Optional<Amount> requestedAmount, CardData cardDataDB) {
        try {
            if (!isCorrectPin(pin, cardDataDB)) {
                throw new IllegalStateException("Incorrect pin");
            }
            if (requestedAmount.isPresent()) {
                if (cardDataDB.getCurrentAmount().amount < requestedAmount.get().amount) {
                    throw new IllegalStateException("Not enough money! Your requested amount of money is:" + requestedAmount.get().amount + " | Your actual amount of money is: " + cardDataDB.getCurrentAmount().amount);
                } else if (cardDataDB.getCurrentAmount().amount >= requestedAmount.get().amount && requestedAmount.get().amount >= 0) {
                    LOG.info("All data correct");
                }
            }
        } catch (Exception e) { //wenn kein amount angegeben wurde also requestedAmount ist empty und sein value ist null
            throw new IllegalStateException(e.getMessage());
        }
    }

    private boolean isCorrectPin(Pin pin, CardData cardDataDB) {
        if (cardDataDB.getPin().equals(pin)) {
            LOG.info("Correct pin");
            return true;
        } else {
            return false;
        }
    }

}
