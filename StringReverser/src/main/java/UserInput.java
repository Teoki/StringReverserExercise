import java.util.Optional;

//data transfer object
public class UserInput {

    public final int choice;

    private UserInput(int choice) {
        this.choice = choice;
    }

    //factory method (wird benutzt um den konstruktor zu validieren)
    public static Optional<UserInput> of(String choice) {
        try {
            return Optional.of(new UserInput(Integer.valueOf(choice))); //liefert einen "!null" Wert zurück
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
