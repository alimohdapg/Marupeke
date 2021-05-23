public class ConsecutiveDiagonal extends Reason{

    private final int x1;
    private final int y1;
    private final int x3;
    private final int y3;
    private final MarupekeTile.Grid grid;

    public ConsecutiveDiagonal(int x1, int y1, int x3, int y3, MarupekeTile.Grid grid) {
        this.x1 = x1;
        this.y1 = y1;
        this.x3 = x3;
        this.y3 = y3;
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "The grid has three consecutive " + grid.getSymbol() + "s in diagonal order from row " + (x1+1) +
                " and column " + (y1+1) + " to row " + (x3+1) + " and column " + (y3+1) + ".";
    }
}
