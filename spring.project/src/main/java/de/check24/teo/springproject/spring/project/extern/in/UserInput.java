package de.check24.teo.springproject.spring.project.extern.in;

import com.google.common.collect.ImmutableList;
import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.AtmAndMenus;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import de.check24.teo.springproject.spring.project.core.dtos.Pin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
            String[] splitInput = args[0].split("/");
            final Predicate<String> idsOnly = e -> !e.equals("atms") && !e.equals("menus") && !e.trim().equals("");

            final List<Integer> list = Arrays.stream(splitInput) //array wird zu stream umgewandelt
                    .filter(idsOnly) //filter geht nur mit streams, deswegen wird das array zu einem stream umgewandelt
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()); //stream wird zu einer list umgewandelt

            final ImmutableList<Integer> menuIds = ImmutableList.copyOf(list.subList(1, list.size())); //nimmt nur die werte von den menus (2,8,88) , weil es mit index 1 startet (index 0 ist id von atms)
            final Integer atmId = list.get(0);

            //if (isNotBlank(inputCardId)) hier die Validierung der args (z.B. ob die länge passt, das richtige eingegeben wurde usw)
            final Optional<Amount> optionalAmount = args.length > 3 ? Optional.of(new Amount(Integer.valueOf(args[3]))) : Optional.empty();

            return Optional.of(new UserInput(new CardId(args[1]), new Pin(args[2]), new AtmAndMenus(atmId, menuIds), optionalAmount)); //TODO restliche dtos -> neues objekt mit args parameter (nach Validation wrs mit variablen) übergeben
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
