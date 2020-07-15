package de.check24.teo.springproject.spring.project.core;

import java.util.Collections;
import java.util.List;

public class MenuButton implements Submittable {

    public final List<MenuButton> menuButtonList; //<-- submenus
    public final int id;
    public final String label;

    public MenuButton(List<MenuButton> menuButtonList, int id, String label) {
        this.menuButtonList = menuButtonList;
        this.id = id;
        this.label = label;
    }

    public MenuButton(int id, String label) {
        this(Collections.emptyList(), id, label);
    }

    @Override
    public int submit(int cardId, int pin) {
        return 0;
    }

}
