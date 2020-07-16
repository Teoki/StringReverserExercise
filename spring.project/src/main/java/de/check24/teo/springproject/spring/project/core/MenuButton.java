package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuButton {

    public final List<MenuButton> subMenuButtonsList; //<-- submenus
    public final int id;
    public final String label;
    public final Optional<Amount> fixedAmount;

    public MenuButton(List<MenuButton> subMenuButtonsList, int id, String label, Amount fixedAmount) {
        this.subMenuButtonsList = subMenuButtonsList;
        this.id = id;
        this.label = label;
        this.fixedAmount = Optional.ofNullable(fixedAmount);
    }

    public MenuButton(List<MenuButton> subMenuButtonsList, int id, String label) {
        this(subMenuButtonsList, id, label, null);
    }

    public MenuButton(int id, String label) {
        this(Collections.emptyList(), id, label, null);
    }

    public void submit(List<Integer> menuIds, CardId cardId, Optional<Amount> amount) {
        if (menuIds.size() == 0) {
            // do action
        } else {
            final Optional<MenuButton> first = subMenuButtonsList.stream().filter(e -> e.id == menuIds.get(0)).findFirst();
            first.get().submit(menuIds.subList(1, menuIds.size()), cardId, fixedAmount.isPresent() ? fixedAmount : amount);
        }

    }

}
