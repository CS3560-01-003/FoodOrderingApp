package LoginP;
import java.sql.*;

public class DBConnection
{
    private Connection myConnection;

    public DBConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingapp", "root", "Jiso1022");
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }

    public Connection connectDB() {
        return myConnection;
    }

}
