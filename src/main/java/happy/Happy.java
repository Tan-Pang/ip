package happy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import happy.task.Task;

public class Happy {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Happy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.load(new ArrayList<>());
        } catch (Exception e) {
            loadedTasks = new ArrayList<>();
        }
        tasks = new TaskList(loadedTasks);
    }

    public void run() {
        ui.printLogo("intro");
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            String line = scanner.nextLine();

            try {
                parser.inputChecker(line, tasks.getTasks().size());
                String command = line.split(" ")[0];

                switch (command) {
                case "bye":
                    ui.printLogo("bye");
                    isRunning = false;
                    break;

                case "hi":
                case "hello":
                    ui.printLogo("hi");
                    break;

                case "list":
                    ui.printLogo("task");
                    for (int i = 0; i < tasks.getTasks().size(); i++) {
                        ui.printCurrItem(i, tasks.getTasks().get(i));
                    }
                    ui.printLogo("line");
                    break;

                case "todo":
                case "deadline":
                case "event":
                    Task added = tasks.addTask(line);
                    storage.save(tasks.getTasks());
                    ui.printTask(added, tasks.getTasks().size() - 1, "add");
                    break;

                case "delete":
                    Task deleted = tasks.deleteTask(line);
                    storage.save(tasks.getTasks());
                    ui.printTask(deleted, tasks.getTasks().size() - 1, "delete");
                    break;

                case "mark":
                case "unmark":
                    Task marked = tasks.markOrUnmarkItem(line, command);
                    storage.save(tasks.getTasks());
                    ui.printMarkOrUnmark(marked, command);
                    break;
                }

            } catch (HappyException e) {
                ui.printErrorMessage(e);
            } catch (IOException e) {
                System.out.println("Error saving tasks.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Happy("data/happy.txt").run();
    }
}