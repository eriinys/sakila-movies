import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DB {
    private final BasicDataSource dataSource;
    private List<Actors> actors;

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

}
