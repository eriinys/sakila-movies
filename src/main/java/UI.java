import java.lang.reflect.Array;
import java.util.*;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private DB db;

    public UI(DB db){
        this.db = db;
    }

    public void HomeScreen() {

        System.out.println("Enter the last name of your favorite actor:");
        String lastName = scanner.nextLine().toLowerCase();
        db.getLastName(lastName);

        System.out.println("Enter the first name and last name of an actor you want to see the movies of:");
        String actorName = scanner.nextLine().toLowerCase();
        String[] nameParts = actorName.trim().split(" ");

        if(nameParts.length == 2){
            String fn = nameParts[0];
            String ln = nameParts[1];
            db.getFilmByActor(fn, ln);
        } else {
            System.out.println("Please enter both first and last name of the actor.");
        }
    }
}
