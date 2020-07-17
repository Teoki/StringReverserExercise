package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

public class Atm {

    public final List<MenuButton> menuButtonList;
    public final int id;

    public Atm(int id, Database database) {
        List<MenuButton> withAndWithOutMenuButtonSubList = Arrays.asList(
                new MenuButton(444, "With Check", database::withdraw),
                new MenuButton(555, "Without Check", database::withdraw)
        );

        final MenuButton customAmount = new MenuButton(withAndWithOutMenuButtonSubList, 4, "Custom Amount");

        final MenuButton withDrawButton = new MenuButton(Arrays.asList(
                new MenuButton(withAndWithOutMenuButtonSubList, 1, "50", new Amount(50)),
                new MenuButton(withAndWithOutMenuButtonSubList, 2, "100", new Amount(100)),
                new MenuButton(withAndWithOutMenuButtonSubList, 3, "500", new Amount(500)),
                customAmount
        ), 11, "With Draw");

        final MenuButton depositButton = new MenuButton(singletonList(new MenuButton(4, "Custom Amount", database::deposit)), 22, "Deposit");

        menuButtonList = new ArrayList<>();
        menuButtonList.add(withDrawButton);
        menuButtonList.add(depositButton);

        this.id = id;
    }

    public Optional<MenuButton> getByMenuButtonId(int id) {
        return menuButtonList.stream().filter(e -> e.id == id).findFirst();
    }

}
