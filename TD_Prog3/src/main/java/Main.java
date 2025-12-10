import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
     DBConnection c = new DBConnection();
        try{
            c.getDBConnection();
            System.out.println("Connected to database successfully");
     } catch (SQLException e) {
            System.out.println("Error connecting to database.");
        }
    }
}
