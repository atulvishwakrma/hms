package H.M.S;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection connection;
    Statement statement;


    public conn(){
        try {
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/H_M_S", "root","radhey@123");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/H_M_S",  // DB URL
                    "example_user",                                    // Username
                    "password"                            // Password
            );
            statement=connection.createStatement();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
