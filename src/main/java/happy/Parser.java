package happy;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> COMMANDS = List.of(
            "hi", "hello", "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete"
    );
    private static final List<String> ACTIONS = List.of(
            "todo", "deadline", "event"
    );

    public Parser() {

    }

    /**
     *Method to check if user input is valid or not.
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
        }
    }
}
