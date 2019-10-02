package tasksByServer.connectionThroughJDBC;


import java.sql.*;



public class firstConnectionToDatabase {
    public static void main(String[] args) {
        //Necessary data for connection to database.
        String url = "jdbc:mysql://localhost:3306/gaaacha"; //Standard URL to localhost database.
        String user = "root";                               //User login.
        String password = "root";                           //User password.
        try {   //Try-catch statement for catching the SQL exception while connection.
            Connection myConn = DriverManager.getConnection(url, user, password);   //Mane connection obj.
            Statement firstOne = myConn.createStatement();  //Statement witch will execute the SQL query.
            String sql = "SELECT * from users";             //SQL query.
            ResultSet rs = firstOne.executeQuery(sql);      //Statement witch will store the result of firstOne.

            while (rs.next())   //The simplest looper.
                System.out.println(rs.getString("username"));

        } catch (SQLException exc) {    //In case of exception show the reason in the console.
            System.out.println(exc.getMessage());
        }
    }
}
