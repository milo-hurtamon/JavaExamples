package util;

public class ToDoListObject {
    // ToDo list table elements definition
    private Integer toDoID;
    private String description;
    private String toDoDate;
    private boolean completed;
    private String observation;

    // Default constructor
    public ToDoListObject() {
        // null values assigned for fetching the whole list of fields
        toDoID = 0;
        description = null;
        toDoDate = null;
        completed = false;
        observation = null;
    }

    // Setter and getter methods for each field
    public Integer getToDoID() {
        return toDoID;
    }

    public void setToDoID (Integer toDoID) {
        this.toDoID = toDoID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToDoDate() {
        return toDoDate;
    }

    public void setToDoDate(String toDoDate) {
        this.toDoDate = toDoDate;
    }

    public boolean getCompleted () {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}