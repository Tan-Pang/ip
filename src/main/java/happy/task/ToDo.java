package happy.task;

/**
 * Represents a ToDo task. A ToDo object corresponds to a task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
