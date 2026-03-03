package happy.task;

/**
 * Represents a deadline task. A Deadline object extends a Task object with a deadline.
 * Deadline object contains a description, marker and deadline.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}