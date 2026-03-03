package happy;

import happy.task.Task;

public class Ui {
    public Ui() {
    }

    /**
     *Print exception error message.
     * @param e Error message to be printed.
     */
    public void printErrorMessage(HappyException e) {
        printLogo("line");
        System.out.print(e.getMessage());
        printLogo("line");
    }

    /**
     *Print requested string(s).
     * @param logoString The string to be printed.
     */
    public void printLogo(String logoString) {
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
        String findLogo = """
            ________________________________________________________________________________
            Here are the matching tasks in your list:
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
        case "find":
            logo = findLogo;
            break;
        default: logo = "";
        }
        System.out.print(logo);
    }

    /**
     *Print added/deleted task with appropriate formatting.
     * @param t Task that was added/deleted.
     * @param currIndex Number of tasks in tasks list.
     * @param action add or delete task.
     */
    public void printTask(Task t, int currIndex, String action) {
        printLogo(action);
        System.out.println("      " + t);
        System.out.printf("    Now you have %d tasks in the list.%n", currIndex + 1);
        printLogo("line");
    }

    public void printMarkedItem(Task markItem) {
        System.out.println("      " + markItem);
    }

    /**
     *Print marked/unmarked item with appropriate formatting.
     * @param markItem Task that is marked/unmarked
     * @param action Mark or unmark.
     */
    public void printMarkOrUnmark(Task markItem, String action) {
        printLogo(action);
        printMarkedItem(markItem);
        printLogo("line");
    }

    /**
     *Print the current task in the list.
     * @param index Index of the input task.
     * @param task The task to be printed
     */
    public void printCurrTask(int index, Task task) {
        System.out.printf("    %d.", index + 1);
        System.out.println(task);
    }

    /**
     * Print message if there are no matching tasks to the keyword given in find command.
     */
    public void printEmptyListMessage() {
        printLogo("line");
        System.out.println("    Sorry there are no matching tasks!");
        printLogo("line");
    }

}
