package dao;

import util.ToDoListObject;

import java.util.List;

public interface ToDoListDAO {
    //  DAO methods definition for the ToDo list excercise
    List<ToDoListObject> fetchToDoList (String fetchType);
    void insertToDoList (ToDoListObject toDoListObject);
    void updateToDoList (ToDoListObject toDoListObject);
    void deleteToDoList (ToDoListObject toDoListObject);
}