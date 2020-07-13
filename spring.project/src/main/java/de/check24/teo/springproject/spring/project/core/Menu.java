package de.check24.teo.springproject.spring.project.core;

import java.util.List;

public class Menu implements Submittable {

    public List<Menu> menuList; //<-- submenus
    public int id;

    @Override
    public int submit(int cardId, int pin) {
        return 0;
    }

}
