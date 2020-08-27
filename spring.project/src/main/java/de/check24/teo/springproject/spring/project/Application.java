package de.check24.teo.springproject.spring.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    /*
    @Autowired
    private Service service; //für dependency Injection und @Component für Inversion of Control, Autowire am besten immer im Konstruktor verwenden, damit leichter getestet werden kann
    */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

   /* @Override
    public void run(String... args) {
        LOG.info("EXECUTING : run() method");
        UserInput.of(args).ifPresent(in -> { //kann empty sein, falls unvalide eingabe
            service.runAction(in.cardId, in.pin, in.atmAndMenus, in.amount);
        });
    }*/

}
