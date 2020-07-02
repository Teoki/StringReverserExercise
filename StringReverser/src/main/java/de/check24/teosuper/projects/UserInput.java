package de.check24.teosuper.projects;

import java.util.Optional;

//data transfer object
public class UserInput {

    public final String choice;

    private UserInput(String choice) {
        this.choice = choice;
    }

    //factory method (wird benutzt um den konstruktor zu validieren)
    public static Optional<UserInput> of(String choice) {
        try {
            final int intChoice = Integer.parseInt(choice);
            //TODO ENUMS ANSTATT INT
            if (intChoice > 2 || intChoice < 1) {
                return Optional.empty();
            }
            return Optional.of(new UserInput(choice));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
