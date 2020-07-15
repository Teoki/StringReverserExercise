package de.check24.teo.springproject.spring.project.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

public class Atm {

    public final List<MenuButton> menuButtonList;
    public final int id;

    public Atm(int id) {
        List<MenuButton> withAndWithOutMenuButtonList = Arrays.asList(
                new MenuButton(444, "With Check"),
                new MenuButton(555, "Without Check")
        );

        final MenuButton customAmount = new MenuButton(withAndWithOutMenuButtonList, 4, "Custom Amount");

        final MenuButton withDrawButton = new MenuButton(Arrays.asList(
                new MenuButton(withAndWithOutMenuButtonList, 1, "50"),
                new MenuButton(withAndWithOutMenuButtonList, 2, "100"),
                new MenuButton(withAndWithOutMenuButtonList, 3, "500"),
                customAmount
        ), 1, "With Draw");

        final MenuButton depositButton = new MenuButton(singletonList(customAmount), 1, "Deposit");

        menuButtonList = new ArrayList<>();
        menuButtonList.add(withDrawButton);
        menuButtonList.add(depositButton);

        this.id = id;
    }
}
