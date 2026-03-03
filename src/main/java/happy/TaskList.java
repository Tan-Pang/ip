package happy;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import happy.task.Deadline;
import happy.task.Event;
import happy.task.Task;
import happy.task.ToDo;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * All the operations of tasks list can be found in TaskList. Namely add, delete, mark/unmark and find task.
     * @param tasks List of current tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *Add new task to tasks list.
     * @param line Input task from user.
     * @return Task that user input in the form of a Task object.
     * @throws HappyException If the format of date input by user is invalid.
     */
    public Task addTask(String line) throws HappyException {
        Task t;
        String[] words = line.split(" ");
        for (String word : words) {
            if (word.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    LocalDate date = LocalDate.parse(word);
                    line = line.replace(word, date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).trim());
                } catch (DateTimeParseException e) {
                    throw new HappyException("""
                                Please input a valid date!
                            """);
                }
            }
        }
        if (line.startsWith("todo")) {
            String description = line.replace("todo", "").trim();
            t = new ToDo(description);
        } else if (line.startsWith("deadline")) {
            String[] splitBy = line.split("\\bby\\b");
            String description = splitBy[0].replace("deadline", "").trim();
            String deadline = splitBy[1].trim();
            t = new Deadline(description, deadline);
        } else { //line starts with "event"
            String[] splitFrom = line.split("\\bfrom\\b");
            String description = splitFrom[0].replace("event", "").trim();
            String[] splitTo = splitFrom[1].split("\\bto\\b");
            String from = splitTo[0].trim();
            String to = splitTo[1].trim();
            t = new Event(description, from, to);
        }
        tasks.add(t);
        return t;
    }

    /**
     *Delete a task and return the deleted task.
     * @param line Input task from user.
     * @return Task to be deleted.
     */
    public Task deleteTask(String line) {
        String indexString = line.replace("delete", "").trim();
        int index = Integer.parseInt(indexString) - 1;
        return tasks.remove(index);
    }

    /**
     *Mark or Unmark a task in tasks list.
     * @param line Input task from user.
     * @param action Mark or Unmark.
     * @return Task to be marked or unmarked.
     */
    public Task markOrUnmarkTask(String line, String action) {
        String[] words = line.split(" ");
        int markIndex = Integer.parseInt(words[1]) - 1;
        Task markItem = tasks.get(markIndex);
        if (action.equals("mark")) {
            markItem.markAsDone();
        } else {
            markItem.unmarkAsDone();
        }
        return markItem;
    }

    /**
     *Returns an ArrayList of tasks that match with given keyword.
     * @param line Input task from user.
     * @return List of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String line) {
        String keyword = line.replace("find", "").trim();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String[] words = task.getDescription().split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    matchingTasks.add(task);
                    break;
                }
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
