package dao;

import core.ToDoDBConnection;
import util.ToDoListObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
TODO:
  Create the interfaces to separate the whole CRUD into detailed functionalities as SOLID propose
 */

public class ToDoListDBCRUD implements ToDoListDAO {
    // Variable creation
    private Statement pgsqlStatement;
    private ResultSet pgsqlFetchResult;
    private String sqlStmnt;
    private String queryToFetch;
    private ToDoListObject workToDoListObject = new ToDoListObject();
    private List<ToDoListObject> workListOfToDoObject;
    private Connection todoDBWorkConn;
    private ToDoDBConnection todoDBConn;

    // Class constructor
    public ToDoListDBCRUD() {
        pgsqlStatement = null;
        sqlStmnt = null;
        pgsqlFetchResult = null;
        queryToFetch = null;
        workListOfToDoObject = new ArrayList<ToDoListObject>();
        workListOfToDoObject.add(workToDoListObject);
        todoDBConn = new ToDoDBConnection();
        todoDBWorkConn = null;
    }

    @Override
    public List<ToDoListObject> fetchToDoList (String fetchType) {
        // Analyzing the type of fetch
        if (fetchType == "A") {
            this.queryToFetch = "SELECT * FROM todolist;";
        } else if (fetchType == "C") {
            this.queryToFetch = "SELECT * FROM todolist WHERE completed != false;";
        } else {
            this.queryToFetch = "SELECT * FROM todolist WHERE completed = false;";
        }

        this.workListOfToDoObject = new ArrayList<ToDoListObject>();
        try {
            this.todoDBWorkConn = this.todoDBConn.getToDoDBConnection();
            this.pgsqlStatement = this.todoDBWorkConn.createStatement();
            this.pgsqlFetchResult = this.pgsqlStatement.executeQuery(this.queryToFetch);

            while (this.pgsqlFetchResult.next()) {
                this.workToDoListObject = new ToDoListObject();
                this.workToDoListObject.setToDoID(this.pgsqlFetchResult.getInt("todoid"));
                this.workToDoListObject.setDescription(this.pgsqlFetchResult.getString("description"));
                this.workToDoListObject.setToDoDate(this.pgsqlFetchResult.getString("todo_date"));
                this.workToDoListObject.setCompleted(this.pgsqlFetchResult.getBoolean("completed"));
                this.workToDoListObject.setObservation(this.pgsqlFetchResult.getString("observation"));
                this.workListOfToDoObject.add(workToDoListObject);
            }

            this.todoDBConn.closeToDoDBConnection(this.todoDBWorkConn);
            return this.workListOfToDoObject;
        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
            return this.workListOfToDoObject;
        }
    }

    // Implements insert function
    /*
     The insert will always ignore the ToDoID due to the pgsql function that adds a new ID
     */
    @Override
    public void insertToDoList (ToDoListObject toDoListObject) {
        this.workToDoListObject = toDoListObject;

        try {
            this.todoDBWorkConn = this.todoDBConn.getToDoDBConnection();
            this.pgsqlStatement = this.todoDBWorkConn.createStatement();

            this.sqlStmnt = "INSERT INTO todolist (description, todo_date, completed, observation) VALUES('" + this.workToDoListObject.getDescription() + "','" + this.workToDoListObject.getToDoDate() + "'," + this.workToDoListObject.getCompleted() + ",'" + this.workToDoListObject.getObservation() + "')";
            this.pgsqlStatement.executeUpdate(this.sqlStmnt);

            this.pgsqlStatement.close();
            this.todoDBConn.closeToDoDBConnection(this.todoDBWorkConn);
        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
        }
    }

    /*
    TODO:
        Verify that the register exist first before try to update because the DB will response with 0 is the structure
        is fine and will not find any kind of problem
     */
    // Implements update function
    @Override
    public void updateToDoList (ToDoListObject toDoListObject) {
        this.workToDoListObject = toDoListObject;

        try {
            this.todoDBWorkConn = this.todoDBConn.getToDoDBConnection();
            this.pgsqlStatement = this.todoDBWorkConn.createStatement();

            this.sqlStmnt = "UPDATE todolist SET description = '" + this.workToDoListObject.getDescription() + "', todo_date = '" + this.workToDoListObject.getToDoDate() + "', completed = " + this.workToDoListObject.getCompleted() + ", observation = '" + this.workToDoListObject.getObservation() + "' WHERE todoid = " + this.workToDoListObject.getToDoID() + ";";
            this.pgsqlStatement.executeUpdate(this.sqlStmnt);

            this.pgsqlStatement.close();
            this.todoDBConn.closeToDoDBConnection(this.todoDBWorkConn);
        } catch (SQLException eSQL) {
            System.err.println(eSQL.getClass().getName()+": "+eSQL.getMessage());
        }
    }

    // Delete function
    @Override
    public void deleteToDoList (ToDoListObject toDoListObject) {
        this.workToDoListObject = toDoListObject;

        try {
            this.todoDBWorkConn = this.todoDBConn.getToDoDBConnection();
            this.pgsqlStatement = this.todoDBWorkConn.createStatement();

            this.sqlStmnt = "DELETE FROM todolist WHERE todoid = " + this.workToDoListObject.getToDoID() + ";";
            this.pgsqlStatement.executeUpdate(this.sqlStmnt);

            this.pgsqlStatement.close();
            this.todoDBConn.closeToDoDBConnection(this.todoDBWorkConn);
        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
        }
    }
}