import java.util.Scanner;
import java.util.Arrays;

public class Happy {
    public static void main(String[] args) {
        String logo = """
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
        System.out.print(logo);
        String line;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        Task[] currList;
        int currIndex = 0;
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                currList = Arrays.copyOf(list, currIndex);
                System.out.print("""
                                     ____________________________________________________________
                                     Here are the tasks in your list:
                                 """);
                for (int i = 0; i < currIndex; i++) {
                    System.out.printf("    %d.[%s] %s%n", i + 1, currList[i].getStatusIcon(), currList[i].description);
                }
                System.out.print("    ____________________________________________________________\n");
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int markIndex = Integer.parseInt(words[1]) - 1;
                Task markItem = list[markIndex];
                markItem.markAsDone();
                System.out.print("""
                             ____________________________________________________________
                             Nice! I've marked this task as done:
                         """);
                System.out.printf("    [%s] %s%n", markItem.getStatusIcon(), markItem.description);
                System.out.print("    ____________________________________________________________\n");
            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int markIndex = Integer.parseInt(words[1]) - 1;
                Task markItem = list[markIndex];
                markItem.unmarkAsDone();
                System.out.print("""
                             ____________________________________________________________
                             OK, I've marked this task as not done yet:
                         """);
                System.out.printf("    [%s] %s%n", markItem.getStatusIcon(), markItem.description);
                System.out.print("    ____________________________________________________________\n");
            } else {
                System.out.print("    ____________________________________________________________\n"
                        + "    added: " + line
                        + "\n    ____________________________________________________________\n");
                Task t = new Task(line);
                list[currIndex] = t;
                currIndex++;
            }
            line = in.nextLine();
        }
        System.out.print(byeLogo);
    }
}
