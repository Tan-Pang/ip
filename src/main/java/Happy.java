import java.util.Scanner;
import java.util.Arrays;

public class Happy {
    public static void main(String[] args) {
        String logo = " ____________________________________________________________\n"
                + " Hello! I'm Happy!\n"
                + " What can I do for you?\n"
                + " ____________________________________________________________\n";
        String byeLogo = " ____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + " ____________________________________________________________\n";
        System.out.print(logo);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.print(" ____________________________________________________________\n" + line
                    + " \n ____________________________________________________________\n");
            line = in.nextLine();
        }
        System.out.print(byeLogo);
    }
}
