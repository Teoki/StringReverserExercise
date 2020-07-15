package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.ArrayList;
import java.util.List;

public class Bank { //Bank is set in config??

    public final List<Atm> atmList;
    public final Database database;
    public final int id;

    public Bank(Database database) {
        this.database = database;
        final Atm atmSSK = new Atm(1);
        final Atm atmTargo = new Atm(2);
        atmList = new ArrayList<>();
        atmList.add(atmSSK);
        atmList.add(atmTargo);
        this.id = 1;
    }
}
