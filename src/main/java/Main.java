import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Application needs two username and password to run.");
            System.exit(1); //exits program when required arguments are not provided with exit code 1
        }

        DB db = new DB (args[0], args[1]);
        UI ui = new UI(db);
        ui.HomeScreen();
    }
}
