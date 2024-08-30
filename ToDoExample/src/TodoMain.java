import dao.ToDoListDBCRUD;
import util.ToDoListObject;
import dao.ToDoListDAO;

public class TodoMain {
    // Trying to resolve the DB Connection
    public static ToDoListObject todoObjInsUpd = new ToDoListObject();

    // Calling the CRUD object
    public static ToDoListDAO toDoListDAOImp = new ToDoListDBCRUD();

    public static void main(String[] args) {
        // Insert function
        todoObjInsUpd.setToDoID(0);
        todoObjInsUpd.setDescription("Insertion from the DAO for the IDK2 time");
        todoObjInsUpd.setToDoDate("2024-08-16");
        todoObjInsUpd.setCompleted(true);
        todoObjInsUpd.setObservation("");
        toDoListDAOImp.insertToDoList(todoObjInsUpd);

        /*
         Options for the Fetch Type
         A: All the records
         C: Completed ones
         D: Will bring only the not completed ones (Default)
        */
        for (ToDoListObject todoFetchWork : toDoListDAOImp.fetchToDoList("A")) {
            System.out.println("ToDo List Information: [ID: " +
                    todoFetchWork.getToDoID() +
                    "] [Description: " +
                    todoFetchWork.getDescription() +
                    "] [Date: " +
                    todoFetchWork.getToDoDate() +
                    "] [Completed: " +
                    todoFetchWork.getCompleted() +
                    "] [Observation: " +
                    todoFetchWork.getObservation() +
                    "]"
            );
        }

        // Update calling
        todoObjInsUpd.setToDoID(28);
        todoObjInsUpd.setDescription("Insertion from the DAO for the ten time");
        todoObjInsUpd.setToDoDate("2024-08-16");
        todoObjInsUpd.setCompleted(true);
        todoObjInsUpd.setObservation("");
        toDoListDAOImp.updateToDoList(todoObjInsUpd);

        // Delete one record
        todoObjInsUpd.setToDoID(29);
        toDoListDAOImp.deleteToDoList(todoObjInsUpd);
    }
}