import java.util.Scanner;

public class Parser {

    private Scanner input;

    public Parser() {
        input = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine = "";
        inputLine = input.nextLine();

        Scanner scanner = new Scanner(inputLine);
        if (scanner.hasNext()) {
            String str = scanner.next();
            CommandWord cw = CommandWord.getCommandWord(str);
            if (cw == CommandWord.UNKNOWN) {
                return new Command(cw, "Unknown command: " + str);
            } else if (cw == CommandWord.QUIT) {
                return new Command(cw, "Bye bye");
            } else if (cw == CommandWord.NEW) {
                if (scanner.hasNextInt()) {
                    int size = scanner.nextInt();
                    if (size < 4 || size > 10) {
                        return new Command(CommandWord.NEW, cw.getWord() + " needs one integer argument greater " +
                                "than 3 and less than 11");
                    }
                    return new Command(cw, size);
                } else {
                    return new Command(CommandWord.NEW, cw.getWord() + " needs one integer argument greater than " +
                            "3 and less than 11");
                }
            } else if (cw == CommandWord.RANDOM) {
                if (scanner.hasNextInt()) {
                    int size = scanner.nextInt();
                    if (size < 4 || size > 10){
                        return new Command(CommandWord.RANDOM, cw.getWord() + " needs four integer " +
                                "arguments separated " + "by spaces, first integer argument needs to be " +
                                "greater than 3 and less than 11");
                    }
                    if (scanner.hasNextInt()) {
                        int fill = scanner.nextInt();
                        if (scanner.hasNextInt()) {
                            int row = scanner.nextInt();
                            if (scanner.hasNextInt()) {
                                int col = scanner.nextInt();
                                if (fill + row + col > ((size * size) / 2)) {
                                    int difference = (fill + row + col) - ((size * size) / 2);
                                    return new Command(CommandWord.RANDOM, cw.getWord() + " needs four integer " +
                                            "arguments separated " + "by spaces, first integer argument needs to be " +
                                            "greater than 3 and less than 11," + " reduce the total sum of the last 3 " +
                                            "arguments  by " + difference);
                                }
                                return new Command(cw, size, fill, row, col);
                            }
                        }
                    }
                }
                return new Command(CommandWord.RANDOM, cw.getWord() + " needs four integer arguments separated " +
                        "by spaces, first integer argument needs to be greater than 3 and less than 11");
            }
            if (scanner.hasNextInt()) {
                int row = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    int col = scanner.nextInt();
                    return new Command(cw, row, col);
                }
            }
            return new Command(CommandWord.UNKNOWN, cw.getWord() + " needs two integer arguments separated by spaces");
        } else {
            return new Command(CommandWord.UNKNOWN, "Please tell me what to do");
        }
    }
}
