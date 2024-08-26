package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* TODO:
*  Change the entire class as a singleton desing pattern
*  Modify the rest of the functions to make sure there is only one call*/

public class ToDoDBConnection {
    //Setting variables to connect with the test DB
    private Connection pgsqlConn;
    private String jdbcURL;
    private String dbUser;
    private String dbPass;

    public ToDoDBConnection() {
        jdbcURL = "jdbc:postgresql://localhost:5432/todo_example";
        dbUser = "pgsql_testrole";
        dbPass = "testingpsswrd";
        pgsqlConn = null;
    }

    //Get the pgsql connection with the DB
    public Connection getToDoDBConnection () {
        //Connect to the DB
        try {
            Class.forName("org.postgresql.Driver");
            this.pgsqlConn = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPass);
            System.out.println("Connection stablished");
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
        }

        //Returning the DB Connection
        return this.pgsqlConn;
    }

    //Close the connection with the DB
    public void closeToDoDBConnection (Connection conn) {
        try {
            this.pgsqlConn = conn;
            this.pgsqlConn.close();
            System.out.println("DB connection closed");
        } catch(SQLException exception) {
            exception.printStackTrace();
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
        }
    }
}