package de.check24.teosuper.projects;

import java.util.Optional;

//data transfer object
public class UserInput {

    public final EnumInputChoices choice;

    private UserInput(EnumInputChoices choice) {
        this.choice = choice;
    }

    //factory method (wird benutzt um den konstruktor zu validieren)
    public static Optional<UserInput> of(String choice) {
        try {
            final EnumInputChoices enumChoice = EnumInputChoices.findByValue(choice);
            return Optional.of(new UserInput(enumChoice));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
