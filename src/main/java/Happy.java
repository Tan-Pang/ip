import java.util.Scanner;
import java.util.Arrays;

public class Happy {

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int currIndex = 0;
        printLogo("hi");
        line = in.nextLine();
        program(line, list, currIndex, in);
        printLogo("bye");
    }

    /**
     *Method that runs while interacting with user.
     */
    private static void program(String line, Task[] list, int currIndex, Scanner in) {
        Task[] currList;
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                currList = Arrays.copyOf(list, currIndex);
                printLogo("task");
                for (int i = 0; i < currIndex; i++) {
                    printCurrItem(i, currList);
                }
                printLogo("line");
            } else if (line.startsWith("mark")) {
                printMarkOrUnmark(line, list, "mark");
            } else if (line.startsWith("unmark")) {
                printMarkOrUnmark(line, list, "unmark");
            } else {
                addTask(line, list, currIndex);
                currIndex++;
            }
            line = in.nextLine();
        }
    }

    /**
     *Method to add new task to Task[] list.
     */
    private static void addTask(String line, Task[] list, int currIndex) {
        Task t;
        if (line.startsWith("todo")) {
            String description = line.replace("todo", "").trim();
            t = new ToDo(description);
        } else if (line.startsWith("deadline")) {
            String[] splitBy = line.split("/by");
            String description = splitBy[0].replace("deadline", "").trim();
            String deadline = splitBy[1].trim();
            t = new Deadline(description, deadline);
        } else if (line.startsWith("event")) {
            String[] splitFrom = line.split("/from");
            String description = splitFrom[0].replace("event", "").trim();
            String[] splitTo = splitFrom[1].split("/to");
            String from = splitTo[0].trim();
            String to = splitTo[1].trim();
            t = new Event(description, from, to);
        } else {
            printLogo("add");
            printAddedItem(line);
            t = new Task(line);
        }
        printTask(t, currIndex);
        list[currIndex] = t;
    }

    /**
     *Method to print added task with appropriate formatting.
     */
    private static void printTask(Task t, int currIndex) {
        printLogo("add");
        System.out.println("      " + t);
        System.out.printf("    Now you have %d tasks in the list.%n", currIndex + 1);
        printLogo("line");
    }

    /**
     *Method to print the requested string(s).
     */
    public static void printLogo(String logoString) {
        String hiLogo = """
            ____________________________________________________________
            Hello! I'm Happy!
            What can I do for you?
            ____________________________________________________________
        """;
        String byeLogo = """
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
        """;
        String taskLogo = """
            ____________________________________________________________
            Here are the tasks in your list:
        """;
        String lineLogo = """
            ____________________________________________________________
        """;
        String markLogo = """
            ____________________________________________________________
            Nice! I've marked this task as done:
        """;
        String unmarkLogo = """
            ____________________________________________________________
            OK, I've marked this task as not done yet:
        """;
        String addLogo = """
            ____________________________________________________________
            Got it. I've added this task:
        """;

        String logo;
        switch (logoString) {
        case "hi":
            logo = hiLogo;
            break;
        case "bye":
            logo = byeLogo;
            break;
        case "task":
            logo = taskLogo;
            break;
        case "line":
            logo = lineLogo;
            break;
        case "mark":
            logo = markLogo;
            break;
        case "unmark":
            logo = unmarkLogo;
            break;
        case "add":
            logo = addLogo;
            break;
        default: logo = "";
        }
        System.out.print(logo);
    }

    /**
     *Method to print newly marked item.
     */
    private static void printMarkedItem(Task markItem) {
        System.out.println("      " + markItem);
    }

    /**
     *Method to mark/unmark item and print it out with appropriate formatting.
     */
    private static void printMarkOrUnmark(String line, Task[] list, String action) {
        String[] words = line.split(" ");
        int markIndex = Integer.parseInt(words[1]) - 1;
        Task markItem = list[markIndex];
        if (action.equals("mark")) {
            markItem.markAsDone();
        } else {
            markItem.unmarkAsDone();
        }
        printLogo(action);
        printMarkedItem(markItem);
        printLogo("line");
    }

    /**
     *Method to print the current item in the list.
     */
    private static void printCurrItem(int i, Task[] currList) {
        System.out.printf("    %d.", i + 1);
        System.out.println(currList[i].toString());
    }

    /**
     *Method to print added item.
     */
    private static void printAddedItem(String line) {
        System.out.println(" " + line);
    }
}
