package happy;

import java.util.ArrayList;

import happy.task.Deadline;
import happy.task.Event;
import happy.task.Task;
import happy.task.ToDo;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *Method to add new task to Task[] list.
     */
    public Task addTask(String line) {
        Task t;
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
     *Method to delete a task and return the deleted task.
     */
    public Task deleteTask(String line) {
        String indexString = line.replace("delete", "").trim();
        int index = Integer.parseInt(indexString) - 1;
        return tasks.remove(index);
    }

    public Task markOrUnmarkItem(String line, String action) {
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
