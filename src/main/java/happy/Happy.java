package happy;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import happy.task.Deadline;
import happy.task.Event;
import happy.task.Task;
import happy.task.ToDo;

public class Happy {
    private static final List<String> COMMANDS = List.of(
            "hi", "hello", "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete"
    );
    private static final List<String> ACTIONS = List.of(
            "todo", "deadline", "event"
    );

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int currIndex = 0;
        printLogo("intro");
        program(list, currIndex, in);
        printLogo("bye");
    }

    /**
     *Method that runs while interacting with user.
     */
    private static void program(ArrayList<Task> list, int currIndex, Scanner in) {
        String line;
        boolean valid;
        do {
            valid = false;
            line = in.nextLine();
            while (!valid) {
                try {
                    inputChecker(line, currIndex);
                    valid = true;
                } catch (HappyException msg) {
                    printErrorMessage(msg);
                    line = in.nextLine();
                }
            }
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("hi") || line.equals("hello")) {
                printLogo("hi");
            } else if (line.equals("list")) {
                printLogo("task");
                for (int i = 0; i < currIndex; i++) {
                    printCurrItem(i, list);
                }
                printLogo("line");
            } else if (line.startsWith("mark")) {
                printMarkOrUnmark(line, list, "mark");
            } else if (line.startsWith("unmark")) {
                printMarkOrUnmark(line, list, "unmark");
            } else if (line.startsWith("delete")) {
                deleteTask(line, list);
                currIndex--;
            } else {
                addTask(line, list, currIndex);
                currIndex++;
            }
        } while (true);
    }

    /**
     *Method to check if user input is valid or not.
     */
    private static void inputChecker(String line, int currIndex) throws HappyException {
        String[] words = line.split(" ");
        String command = words[0];
        if (!COMMANDS.contains(command)) {
            throw new HappyException("""
                Sorry, I don't understand. :(
                Please enter an appropriate command. Thank you! :D
            """);
        } if (ACTIONS.contains(command)) {
            int numOfWords = words.length;
            if (numOfWords == 1) {
                throw new HappyException("""
                            I can't add an empty task!
                            Please add something!
                        """);
            }
        }
        switch (command) {
        case "deadline":
            if (!Arrays.asList(words).contains("by")) {
                throw new HappyException("""
                        So sorry but Deadline type requires a deadline using the keyword "by".
                    """);
            }
            break;
        case "event":
            if (!Arrays.asList(words).contains("to") || !Arrays.asList(words).contains("from")) {
                throw new HappyException("""
                        So sorry but Event type requires a duration using the keywords "to" and "from".
                    """);
            }
            break;
        case "delete":
            if (words.length > 2 || !words[1].matches("\\d+")) {
                throw new HappyException("""
                        Please tell me what task number you want me to delete thank you!
                    """);
            }
            int deleteIndex = Integer.parseInt(words[1]);
            if (deleteIndex < 1 || deleteIndex > currIndex) {
                throw new HappyException("""
                        Index out of bounds! Please give me a valid task number!
                    """);
            }
            break;
        }
    }

    /**
     *Method to print exception error message.
     */
    private static void printErrorMessage(HappyException msg) {
        printLogo("line");
        System.out.print(msg.getMessage());
        printLogo("line");
    }

    /**
     *Method to add new task to Task[] list.
     */
    private static void addTask(String line, ArrayList<Task> list, int currIndex) {
        Task t;
        if (line.startsWith("todo")) {
            String description = line.replace("todo", "").trim();
            t = new ToDo(description);
        } else if (line.startsWith("deadline")) {
            String[] splitBy = line.split("\\bby\\b");
            String description = splitBy[0].replace("deadline", "").trim();
            System.out.println(Arrays.toString(splitBy));
            String deadline = splitBy[1].trim();
            System.out.println(deadline);
            t = new Deadline(description, deadline);
        } else { //line starts with "event"
            String[] splitFrom = line.split("\\bfrom\\b");
            String description = splitFrom[0].replace("event", "").trim();
            String[] splitTo = splitFrom[1].split("\\bto\\b");
            String from = splitTo[0].trim();
            String to = splitTo[1].trim();
            t = new Event(description, from, to);
        }
        printTask(t, currIndex, "add");
        list.add(t);
    }

    private static void deleteTask(String line, ArrayList<Task> list) {
        String indexString = line.replace("delete", "").trim();
        int index = Integer.parseInt(indexString) - 1;
        Task deletedTask = list.get(index);
        list.remove(index);
        printTask(deletedTask, index - 1, "delete");
    }


        /**
         *Method to print added task with appropriate formatting.
         */
    private static void printTask(Task t, int currIndex, String action) {
        printLogo(action);
        System.out.println("      " + t);
        System.out.printf("    Now you have %d tasks in the list.%n", currIndex + 1);
        printLogo("line");
    }

    /**
     *Method to print the requested string(s).
     */
    public static void printLogo(String logoString) {
        String introLogo = """
            ________________________________________________________________________________
            Hello! I'm Happy!
            What can I do for you?
            ________________________________________________________________________________
        """;
        String hiLogo = """
            ________________________________________________________________________________
            Hi there!
            ________________________________________________________________________________
        """;
        String byeLogo = """
            ________________________________________________________________________________
            Bye. Hope to see you again soon!
            ________________________________________________________________________________
        """;
        String taskLogo = """
            ________________________________________________________________________________
            Here are the tasks in your list:
        """;
        String lineLogo = """
            ________________________________________________________________________________
        """;
        String markLogo = """
            ________________________________________________________________________________
            Nice! I've marked this task as done:
        """;
        String unmarkLogo = """
            ________________________________________________________________________________
            OK, I've marked this task as not done yet:
        """;
        String addLogo = """
            ________________________________________________________________________________
            Got it. I've added this task:
        """;
        String deleteLogo = """
            ________________________________________________________________________________
            Noted. I've removed this task:
        """;

        String logo;
        switch (logoString) {
        case "intro":
            logo = introLogo;
            break;
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
        case "delete":
            logo = deleteLogo;
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
    private static void printMarkOrUnmark(String line, ArrayList<Task> list, String action) {
        String[] words = line.split(" ");
        int markIndex = Integer.parseInt(words[1]) - 1;
        Task markItem = list.get(markIndex);
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
    private static void printCurrItem(int i, ArrayList<Task> currList) {
        System.out.printf("    %d.", i + 1);
        System.out.println(currList.get(i));
    }
}
