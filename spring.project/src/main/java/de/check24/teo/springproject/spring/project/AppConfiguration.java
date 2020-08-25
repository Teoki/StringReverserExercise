package de.check24.teo.springproject.spring.project;

import de.check24.teo.springproject.spring.project.core.Atm;
import de.check24.teo.springproject.spring.project.core.MenuButton;
import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.*;

@Configuration
public class AppConfiguration {

    @Autowired
    @Bean
    public MenuButton withCheck(Database database) {
        return new MenuButton(444, "With Check", database::withdraw);
    }

    @Autowired
    @Bean
    public MenuButton withOutCheck(Database database) {
        return new MenuButton(555, "Without Check", database::withdraw);
    }

    @Autowired
    @Bean
    public List<MenuButton> withAndWithOutMenuButtonSubList(@Qualifier("withCheck") MenuButton withCheck, @Qualifier("withOutCheck") MenuButton withOutCheck) {
        return unmodifiableList(Arrays.asList(
                withCheck,
                withOutCheck
        ));
    }

    @Autowired
    @Bean
    public MenuButton fiftyMenuButton(@Qualifier("withAndWithOutMenuButtonSubList") List<MenuButton> withAndWithOutMenuButtonSubList) {
        return new MenuButton(withAndWithOutMenuButtonSubList, 1, "50", new Amount(50));
    }

    @Autowired
    @Bean
    public MenuButton hundredMenuButton(@Qualifier("withAndWithOutMenuButtonSubList") List<MenuButton> withAndWithOutMenuButtonSubList) {
        return new MenuButton(withAndWithOutMenuButtonSubList, 2, "100", new Amount(50));
    }

    @Autowired
    @Bean
    public MenuButton fiveHundredMenuButton(@Qualifier("withAndWithOutMenuButtonSubList") List<MenuButton> withAndWithOutMenuButtonSubList) {
        return new MenuButton(withAndWithOutMenuButtonSubList, 3, "500", new Amount(50));
    }

    @Autowired
    @Bean
    public MenuButton customAmount(@Qualifier("customAmount") List<MenuButton> withAndWithOutMenuButtonSubList) {
        return new MenuButton(withAndWithOutMenuButtonSubList, 4, "Custom Amount");
    }

    @Autowired
    @Bean
    public MenuButton withDrawButton(@Qualifier("fiftyMenuButton") MenuButton fiftyMenuButton, @Qualifier("hundredMenuButton") MenuButton hundredMenuButton,
                                     @Qualifier("fiveHundredMenuButton") MenuButton fiveHundredMenuButton, @Qualifier("customAmount") MenuButton customAmount) {
        return new MenuButton(Arrays.asList(
                fiftyMenuButton,
                hundredMenuButton,
                fiveHundredMenuButton,
                customAmount
        ), 11, "With Draw");
    }

    @Autowired
    @Bean
    public List<MenuButton> menuButtonList(@Qualifier("withDrawButton") MenuButton withDrawButton, @Qualifier("depositButton") MenuButton depositButton) {
        return unmodifiableList(Arrays.asList(
                withDrawButton,
                depositButton
        ));
    }

    @Autowired
    @Bean
    public MenuButton depositButton(Database database) {
        return new MenuButton(singletonList(new MenuButton(4, "Custom Amount", database::deposit)), 22, "Deposit");
    }

    @Autowired
    @Bean
    public Atm atmOne(@Qualifier("menuButtonList") List<MenuButton> menuButtonList) {
        return new Atm(111, menuButtonList);
    }

    @Bean
    public Atm atmTwo() {
        return new Atm(222, emptyList());
    }

}
