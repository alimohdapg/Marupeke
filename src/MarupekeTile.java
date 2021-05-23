public class MarupekeTile {

    private boolean editable;
    private Grid grid;
    private boolean visited;

    enum Grid {
        CROSS('X'), CIRCLE('O'), SOLID('#'), BLANK('_');

        private final char symbol;

        public char getSymbol() {
            return this.symbol;
        }

        Grid(char symbol) {
            this.symbol = symbol;
        }
    }

    public MarupekeTile() {
        visited = false;
        editable = true;
        grid = Grid.BLANK;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
