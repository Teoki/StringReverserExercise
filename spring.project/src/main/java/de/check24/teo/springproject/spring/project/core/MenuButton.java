package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class MenuButton {

    public final List<MenuButton> subMenuButtonsList; //<-- submenus
    public final int id;
    public final String label;
    public final Optional<Amount> fixedAmount;
    private BiConsumer<CardId, Amount> callDB;

    public MenuButton(List<MenuButton> subMenuButtonsList, int id, String label, Amount fixedAmount) {
        this.subMenuButtonsList = subMenuButtonsList;
        this.id = id;
        this.label = label;
        this.fixedAmount = Optional.ofNullable(fixedAmount);
        this.callDB = null;
    }

    public MenuButton(List<MenuButton> subMenuButtonsList, int id, String label) {
        this(subMenuButtonsList, id, label, null); //null, weil dieser Wert nie aufgerufen wird (siehe "submit()" unten)
    }

    public MenuButton(int id, String label, BiConsumer<CardId, Amount> callDB) {
        this(Collections.emptyList(), id, label);
        this.callDB = callDB;
    }

    public void submit(List<Integer> menuIds, CardId cardId, Optional<Amount> amount) {
        if (menuIds.size() == 0) {
            System.out.println("SUBMIT STARTED");
            callDB.accept(cardId, amount.get());
            System.out.println("SUBMIT FINISHED");
        } else {
            final Optional<MenuButton> first = subMenuButtonsList.stream().filter(e -> e.id == menuIds.get(0)).findFirst(); //ruf solange die subMenus auf bis menuIds == 0 sind
            if (first.isPresent()) {
                first.get().submit(menuIds.subList(1, menuIds.size()), cardId, fixedAmount.isPresent() ? fixedAmount : amount);
            } else {
                System.out.println("You didn't choose with or without check (444 With Check or 555 Without Check)");
            }
        }
    }

}
