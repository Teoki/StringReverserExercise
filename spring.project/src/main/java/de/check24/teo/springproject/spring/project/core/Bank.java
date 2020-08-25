package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class Bank {

    public final List<Atm> atmList;
    public final Database database;
    public final int id;

    @Autowired
    public Bank(Database database, @Qualifier("atmOne") Atm atmOne, @Qualifier("atmTwo") Atm atmTwo) { //da mit @Autowired annotiert, sucht Spring nach einer @Component (im Inversion of Control-context), die Database implementiert. Bei mehreren Implementierungen von Database gibt es einen Error
        this.database = database;

        final List<Atm> atmList = new ArrayList<>();
        atmList.add(atmOne);
        atmList.add(atmTwo);

        this.atmList = Collections.unmodifiableList(atmList);
        this.id = 1;
    }

    public Optional<Atm> getByAtmId(int id) {

        return atmList.stream().filter(e -> e.id == id).findFirst();

    }
}
