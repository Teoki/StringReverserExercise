package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank { //Bank is set in config??

    public final List<Atm> atmList;
    public final Database database;
    public final int id;

    public Bank(Database database) {
        this.database = database;
        final Atm atmOne = new Atm(111, database);
        final Atm atmTwo = new Atm(222, database);
        atmList = new ArrayList<>();
        atmList.add(atmOne);
        atmList.add(atmTwo);
        this.id = 1;
    }

    public Optional<Atm> getByAtmId(int id) {

        return atmList.stream().filter(e->e.id == id).findFirst();

    }
}
