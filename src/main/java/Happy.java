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
        String[] list = new String[100];
        String[] currList = new String[100];
        int currIndex = 0;
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                currList = Arrays.copyOf(list, currIndex);
                System.out.print(" ____________________________________________________________\n");
                for (int i = 0; i < currIndex; i++) {
                    System.out.printf("%d. %s%n", i+1, currList[i]);
                }
                System.out.print(" ____________________________________________________________\n");
            } else {
                System.out.print(" ____________________________________________________________\n"
                        + " added: " + line
                        + " \n ____________________________________________________________\n");
                list[currIndex] = line;
                currIndex++;
            }
            line = in.nextLine();
        }
        System.out.print(byeLogo);
    }
}
