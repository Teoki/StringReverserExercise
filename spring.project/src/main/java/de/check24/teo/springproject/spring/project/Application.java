package de.check24.teo.springproject.spring.project;

import de.check24.teo.springproject.spring.project.core.Bank;
import de.check24.teo.springproject.spring.project.core.Service;
import de.check24.teo.springproject.spring.project.extern.in.UserInput;
import de.check24.teo.springproject.spring.project.extern.out.database.mydatabase.MyDatabaseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Application.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : run() method");
        final Bank bank = new Bank(new MyDatabaseImpl());
        final Service service = new Service(bank.database);

        Optional<UserInput> input = UserInput.of(args); //kann empty sein, falls unvalide eingabe
        input.ifPresent(in -> {
            //service.runAtmAndMenuAction();
            service.runAction(input.get().cardId, input.get().pin, input.get().atmAndMenus, input.get().amount);
        });
    }
}
