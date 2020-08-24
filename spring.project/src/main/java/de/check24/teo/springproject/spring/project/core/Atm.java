package de.check24.teo.springproject.spring.project.core;

import java.util.List;
import java.util.Optional;

public class Atm {

    public final List<MenuButton> menuButtonList;
    public final int id;

    public Atm(int id, List<MenuButton> menuButtonList) {
        this.menuButtonList = menuButtonList;
        this.id = id;
    }

    public Optional<MenuButton> getByMenuButtonId(int id) {
        return menuButtonList.stream().filter(e -> e.id == id).findFirst();
    }

}
