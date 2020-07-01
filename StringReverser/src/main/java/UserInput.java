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
            final int intChoice = Integer.parseInt(choice);
            if (intChoice > 2 || intChoice < 1) {
                return Optional.empty();
            }
            return Optional.of(new UserInput(Integer.valueOf(choice)));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
