package happy;

import happy.task.Task;

public class Ui {
    public Ui() {
    }

    /**
     *Method to print exception error message.
     */
    public void printErrorMessage(HappyException e) {
        printLogo("line");
        System.out.print(e.getMessage());
        printLogo("line");
    }

    /**
     *Method to print the requested string(s).
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
     *Method to print added task with appropriate formatting.
     */
    public void printTask(Task t, int currIndex, String action) {
        printLogo(action);
        System.out.println("      " + t);
        System.out.printf("    Now you have %d tasks in the list.%n", currIndex + 1);
        printLogo("line");
    }


    /**
     *Method to print newly marked item.
     */
    public void printMarkedItem(Task markItem) {
        System.out.println("      " + markItem);
    }

    /**
     *Method to print marked/unmarked item with appropriate formatting.
     */
    public void printMarkOrUnmark(Task markItem, String action) {
        printLogo(action);
        printMarkedItem(markItem);
        printLogo("line");
    }

    /**
     *Method to print the current item in the list.
     */
    public void printCurrItem(int index, Task task) {
        System.out.printf("    %d.", index + 1);
        System.out.println(task);
    }

    public void printEmptyListMessage() {
        printLogo("line");
        System.out.println("    Sorry there are no matching tasks!");
        printLogo("line");
    }

}
