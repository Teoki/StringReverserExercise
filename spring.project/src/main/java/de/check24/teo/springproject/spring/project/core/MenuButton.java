package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class MenuButton {

    private static Logger LOG = LoggerFactory.getLogger(MenuButton.class);
    public final List<MenuButton> subMenuButtonsList; //<-- submenus
    public final int id;
    public final String label;
    public final Optional<Amount> fixedAmount;
    private BiFunction<CardId, Amount, Integer> callDB;

    public MenuButton(List<MenuButton> subMenuButtonsList, int id, String label, Amount fixedAmount) {
        this.subMenuButtonsList = Collections.unmodifiableList(subMenuButtonsList);
        this.id = id;
        this.label = label;
        this.fixedAmount = Optional.ofNullable(fixedAmount);
        this.callDB = null;
    }

    public MenuButton(List<MenuButton> subMenuButtonsList, int id, String label) {
        this(subMenuButtonsList, id, label, null); //null, weil dieser Wert nie aufgerufen wird (siehe "submit()" unten)
    }

    public MenuButton(int id, String label, BiFunction<CardId, Amount, Integer> callDB) {
        this(Collections.emptyList(), id, label);
        this.callDB = callDB;
    }

    public Integer submit(List<Integer> menuIdList, CardId cardId, Optional<Amount> amount) {
        if (menuIdList.size() == 0) {
            return callDB.apply(cardId, amount.get());
        } else {
            final Optional<MenuButton> first = subMenuButtonsList.stream().filter(e -> e.id == menuIdList.get(0)).findFirst(); //ruf solange die subMenus auf bis menuIds == 0 sind
            if (first.isPresent()) {
                return first.get().submit(menuIdList.subList(1, menuIdList.size()), cardId, fixedAmount.isPresent() ? fixedAmount : amount);
            } else {
                throw new IllegalStateException("You didn't choose with or without check (444 With Check or 555 Without Check)");
            }
        }
    }

}
