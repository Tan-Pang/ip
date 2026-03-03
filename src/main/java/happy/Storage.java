package happy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import happy.task.Deadline;
import happy.task.Event;
import happy.task.Task;
import happy.task.ToDo;

public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Represents the storage of the list of tasks.
     * @param filePath A relative path to the text file that stores the list of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Returns the string version of the task as if the user entered it.
     *
     * @param task Task object.
     * @return String that user will type to input that task.
     */
    public String taskToString(Task task) {
        String taskString = "unmarked";
        if (task.isDone()) {
            taskString = "marked";
        }
        if (task instanceof ToDo) {
            taskString += "|todo " + task.getDescription();
        } else if (task instanceof Deadline) {
            taskString += "|deadline " + task.getDescription() + " by " + ((Deadline) task).getBy();
        } else {
            taskString += "|event " + task.getDescription() + " from " + ((Event) task).getFrom() + " to " + ((Event) task).getTo();
        }
        return taskString;
    }

    /**
     * Returns the tasks list in the form of ArrayList by loading the text in the text file that stores the tasks.
     *
     * @param tasks ArrayList of Task objects.
     * @return Updated tasks list.
     * @throws FileNotFoundException If the input text file does not exist.
     */
    public ArrayList<Task> load(ArrayList<Task> tasks) throws FileNotFoundException {
        try (Scanner s = new Scanner(file)) {
            Task task;
            while (s.hasNext()) {
                String[] parts = s.nextLine().split("\\|");
                String mark = parts[0];
                if (parts.length < 2) continue;
                String content = parts[1];
                if (content.startsWith("todo")) {
                    String description = content.replace("todo", "").trim();
                    task = new ToDo(description);
                } else if (content.startsWith("deadline")) {
                    String[] splitBy = content.split("\\bby\\b");
                    String description = splitBy[0].replace("deadline", "").trim();
                    String by = splitBy[1].trim();
                    task = new Deadline(description, by);
                } else { // event
                    String[] splitFrom = content.split("\\bfrom\\b");
                    String description = splitFrom[0].replace("event", "").trim();
                    String[] splitTo = splitFrom[1].split("\\bto\\b");
                    String from = splitTo[0].trim();
                    String to = splitTo[1].trim();
                    task = new Event(description, from, to);
                }

                if (mark.equals("marked")) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks in tasks list to the text file. Creates a new text file with the given file path if it does
     * not exist.
     *
     * @param tasks ArrayList of Task objects.
     * @throws IOException If the input text file does not exist.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("Created a new file at '" + filePath + "' to store tasks.");
            }
        }
        String taskString;
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                taskString = taskToString(task);
                fw.write(taskString + System.lineSeparator());
            }
        }
    }
}
