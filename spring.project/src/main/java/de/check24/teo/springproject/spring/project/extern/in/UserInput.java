package de.check24.teo.springproject.spring.project.extern.in;

import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.AtmAndMenus;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import de.check24.teo.springproject.spring.project.core.dtos.Pin;

import java.util.Optional;

public class UserInput {

    public final CardId cardId;
    public final Pin pin;
    public final AtmAndMenus atmAndMenus;
    public final Optional<Amount> amount;

    public UserInput(CardId cardId, Pin pin, AtmAndMenus atmAndMenus, Optional<Amount> amount) {
        this.cardId = cardId;
        this.pin = pin;
        this.atmAndMenus = atmAndMenus;
        this.amount = amount;
    }

    public static Optional<UserInput> of(String... args) {
        try {
            //if (isNotBlank(inputCardId)) hier die Validierung der args (z.B. ob die länge passt, das richtige eingegeben wurde usw)
            return Optional.of(new UserInput(new CardId(args[1], new Pin()))); //TODO restliche dtos -> konstruktoren usw erstellen (wie CardId) und neues objekt mit args parameter (nach Validation wrs mit variablen) übergeben
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
