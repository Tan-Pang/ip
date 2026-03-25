package happy;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final List<String> COMMANDS = List.of(
            "hi", "hello", "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find"
    );
    private static final List<String> ACTIONS = List.of(
            "todo", "deadline", "event"
    );

    public Parser() {
    }

    /**
     * Checks if user input is valid or not.
     *
     * @param line The input from user in the form of a String.
     * @param currIndex The number of tasks in tasks list.
     * @throws HappyException If the input is invalid.
     */
    public void inputChecker(String line, int currIndex) throws HappyException {
        if (line == null || line.trim().isEmpty()) {
            throw new HappyException("""
                Please enter something!
            """);
        }
        String[] words = line.split(" ");
        List<String> wordList = Arrays.asList(words);
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
        for (String word : words) {
            if (word.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    LocalDate.parse(word);
                } catch (DateTimeParseException e) {
                    throw new HappyException("""
                                Please input a valid date!
                            """);
                }
            }
        }
        commandChecker(currIndex, command, wordList, words);
    }

    private static void commandChecker(int currIndex, String command, List<String> wordList, String[] words) throws HappyException {
        switch (command) {
        case "deadline":
            if (!wordList.contains("by")) {
                throw new HappyException("""
                            So sorry but Deadline type requires a deadline using the keyword "by".
                        """);
            }
            break;
        case "event":
            if (!wordList.contains("to") || !wordList.contains("from")) {
                throw new HappyException("""
                            So sorry but Event type requires a duration using the keywords "to" and "from".
                        """);
            }
            break;
        case "delete":
            if (words.length != 2 || !words[1].matches("\\d+")) {
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
        case "find":
            if (words.length != 2) {
                throw new HappyException("""
                            Sorry I can't find nothing!
                        """);
            }
            break;
        case "mark":
        case "unmark":
            try {
                if (words.length != 2 || Integer.parseInt(words[1]) > currIndex || Integer.parseInt(words[1]) < 0) {
                    throw new HappyException("""
                                Sorry invalid task to mark/unmark!
                            """);
                }
            } catch (NumberFormatException e) {
                throw new HappyException("""
                                Please enter an index to mark/unmark the task!
                            """);
            }
            break;
        }
    }
}
