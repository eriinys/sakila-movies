import java.lang.reflect.Array;
import java.util.*;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private DB db;

    public UI(DB db){
        this.db = db;
    }

    public void HomeScreen() {
        boolean in = true;
        while(in) {
            System.out.println("""
                    What do you want to do?
                    1) Search actor by last name
                    2) Search film by actor's first and last name
                    3) Search film by actor ID
                    0) Exit
                    """);
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1 -> {
                    System.out.println("Enter the last name of your favorite actor:");
                    String lastName = scanner.nextLine().toLowerCase();
                    db.getLastName(lastName);
                }
                case 2 -> {
                    System.out.println("Enter the first name and last name of an actor you want to see the movies of:");
                    String actorName = scanner.nextLine().toLowerCase();
                    String[] nameParts = actorName.trim().split(" ");

                    if (nameParts.length == 2) {
                        String fn = nameParts[0];
                        String ln = nameParts[1];
                        db.getFilmByActorName(fn, ln);
                    } else {
                        System.out.println("Please enter both first and last name of the actor.");
                    }
                }
                case 3 ->{
                    System.out.println("Enter the actor id to search for film by:");
                    int actorId = Integer.parseInt(scanner.nextLine());
                    db.getFilmByActorId(actorId);
                }
                case 0 -> {
                    System.out.println("Exiting program...Good bye!");
                    in = false;
                }
            }
        }
    }
}
