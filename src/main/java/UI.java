import java.util.*;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private DB db;
    private Actors actor;

    public UI(DB db){
        this.db = db;
        this.actor = new Actors();
    }

    public void HomeScreen() {

        System.out.println("Enter the last name of your favorite actor:");
        String lastName = scanner.nextLine().toLowerCase();
        actor.setLastName(lastName);
        db.getLastName(lastName);
    }
}
