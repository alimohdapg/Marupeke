public class PlayMarupeke {
    private final Parser parser;
    private MarupekeGrid marupekeGrid;
    private boolean gridInitialised;

    public PlayMarupeke() {
        parser = new Parser();
        gridInitialised = false;
    }

    private void execute(Command c) throws Exception {
        switch (c.getCommand()) {
            case NEW:
                if (c.getSize() > 3 && c.getSize() < 11) {
                    marupekeGrid = new MarupekeGrid(c.getSize());
                    gridInitialised = true;
                }
                System.out.println(c);
                break;
            case RANDOM:
                if (c.getSize() > 3 && c.getSize() < 11) {
                    marupekeGrid = MarupekeGrid.randomPuzzle(c.getSize(), c.getFill(), c.getRow(), c.getColumn());
                    gridInitialised = true;
                }
                System.out.println(c);
                break;
            case MARKO:
                if (gridInitialised && c.getRow() > 0 && c.getColumn() > 0 && c.getRow() < marupekeGrid.getMarupekeTiles().length + 1
                        && c.getColumn() < marupekeGrid.getMarupekeTiles().length + 1) {
                    marupekeGrid.markO(c.getRow() - 1, c.getColumn() - 1);
                }
                System.out.println(c);
                break;
            case MARKX:
                if (gridInitialised && c.getRow() > 0 && c.getColumn() > 0 && c.getRow() < marupekeGrid.getMarupekeTiles().length + 1
                        && c.getColumn() < marupekeGrid.getMarupekeTiles().length + 1) {
                    marupekeGrid.markX(c.getRow() - 1, c.getColumn() - 1);
                }
                System.out.println(c);
                break;
            case CLEAR:
                if (gridInitialised && c.getRow() > 0 && c.getColumn() > 0 && c.getRow() < marupekeGrid.getMarupekeTiles().length + 1
                        && c.getColumn() < marupekeGrid.getMarupekeTiles().length + 1) {
                    if(!marupekeGrid.unMark(c.getRow() - 1, c.getColumn() - 1)){
                        System.out.println("Can't clear non-editable tile");
                    } else {
                        System.out.println("Cleared Tile");
                    }
                }
                System.out.println(c);
                break;
            case QUIT:
                break;
            default:
                System.out.println(c);
        }
        System.out.println(c.getMsg());
    }


    private void commandLine() throws Exception {
        printPrompt("New Game");
        Command c = parser.getCommand();
        boolean complete = false;
        while (c.getCommand() != CommandWord.QUIT) {
            execute(c);
            if (gridInitialised) {
                printPrompt(marupekeGrid.toString());
            } else {
                printPrompt("");
            }
            if (gridInitialised) {
                complete = marupekeGrid.isPuzzleComplete();
            }
            if (complete) {
                System.out.println("WELL DONE!");
                break;
            }
            c = parser.getCommand();
        }
        System.out.println("Bye bye");
    }

    private void printPrompt(String msg) {
        System.out.println(msg);
        System.out.print(">");
    }

    public static void main(String[] args) throws Exception {
        PlayMarupeke pM = new PlayMarupeke();
        pM.commandLine();
    }
}
