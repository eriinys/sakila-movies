import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DB {
    private final BasicDataSource dataSource;
    private List<Actors> actors;
    private List<Films> films;

    public DB(String user, String password) {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
    }

    public List<Actors> getLastName(String lastName){
        actors = new ArrayList<>();
        String sql = "SELECT DISTINCT first_name, last_name FROM actor";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()) {
                String FirstName = rs.getString("first_name");
                String LastName = rs.getString("last_name");

                Actors actor = new Actors(FirstName, LastName);
                if (LastName.equalsIgnoreCase(lastName)) {
                    actors.add(actor);
                }
            }

                if(actors.isEmpty()){
                    System.out.println("No matching actor found.");
                } else {
                    for (Actors a : actors){
                        System.out.println(a.getFirstName() + " " + a.getLastName());
                    }
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Films> getFilmByActor(String firstName, String lastName){
        films = new ArrayList<>();
        String sql = "SELECT DISTINCT film.title FROM film " +
                "JOIN film_actor ON film.film_id = film_actor.film_id " +
                "JOIN actor ON film_actor.actor_id =  actor.actor_id " +
                "WHERE LOWER(actor.first_name) = ? " +
                "AND LOWER(actor.last_name) = ?";

        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, firstName);
            ps.setString(2, lastName);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    String title = rs.getString("title");

                    Films film = new Films(title);
                    films.add(film);
                }
            }

            if(films.isEmpty()){
                System.out.println("No matching film found.");
            } else {
                for (Films f : films){
                    System.out.println(f.getTitle());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

}
