package de.check24.teo.springproject.spring.project;

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

    //TODO für jeden Parameter eine Validierungsklasse erstellen wie bei der Übung davor (insgesamt 4 Validierungsklassen mit dem "Optional") (--> 1 Erledigt für cardId)
    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        final MyDatabaseImpl database = new MyDatabaseImpl();
        //input kann jetzt "empty" sein (falls die cardId falsch ist)
        Optional<UserInput> input = UserInput.of(args[1]); //--> ist momentan empty weil args[0] /atms/1/menus/2/menus/8/menus/88 ist und nicht nur "1234567"

        //TODO etwas mit pin und kontostand machen
        if (input.isPresent()) {
            if (input.get().cardData.getPin().equals(args[2])) {
                LOG.info("Correct Pin");
            }
            //else return LOG.info("Wrong pin");
            input.get().cardData.getCurrentAmount();
        }

        for (int i = 0; i <= 9; i++) {
            String[] splitInput = args[0].split("/");
            String choice = splitInput[i];
            LOG.info(i + ". Wert: " + choice);

            //if (findById(choice)) gib mir die bank/itm/menu

            //if (choice.equals("atms")){
            // ATM atm = findByAtmId(choice); <-- gives Object of chosen ATM
            //
            // }

        }

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}
