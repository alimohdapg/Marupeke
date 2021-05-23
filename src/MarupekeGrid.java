import java.util.*;

public class MarupekeGrid {

    private MarupekeTile[][] marupekeTiles;
    private List<Reason> reasonList = new LinkedList<>();

    public MarupekeGrid(int size) {
        if (size > 10) {
            marupekeTiles = new MarupekeTile[10][10];
        } else if (size < 3) {
            marupekeTiles = new MarupekeTile[3][3];
        } else {
            marupekeTiles = new MarupekeTile[size][size];
        }
        // initialise all positions in the marupekeTiles 2D array
        for (int i = 0; i < marupekeTiles.length; i++) {
            for (int j = 0; j < marupekeTiles.length; j++) {
                marupekeTiles[i][j] = new MarupekeTile();
            }
        }
    }

    public boolean setSolid(int x, int y) {
        if (!marupekeTiles[x][y].isEditable()) {
            return false;
        }
        marupekeTiles[x][y].setGrid(MarupekeTile.Grid.SOLID);
        marupekeTiles[x][y].setEditable(false);
        return true;
    }

    public boolean setX(int x, int y, boolean canEdit) {
        if (!marupekeTiles[x][y].isEditable()) {
            return false;
        }
        marupekeTiles[x][y].setGrid(MarupekeTile.Grid.CROSS);
        marupekeTiles[x][y].setEditable(canEdit);
        return true;
    }

    public boolean setO(int x, int y, boolean canEdit) {
        if (!marupekeTiles[x][y].isEditable()) {
            return false;
        }
        marupekeTiles[x][y].setGrid(MarupekeTile.Grid.CIRCLE);
        marupekeTiles[x][y].setEditable(canEdit);
        return true;
    }

    public static MarupekeGrid randomPuzzle(int size, int numFill, int numX, int numO) throws Exception {
        MarupekeGrid finishablePuzzle;
        finishablePuzzle = randomPuzzleBuilder(size, numFill, numX, numO);
        if (size > 6) {
            while (!finishablePuzzle.isLegalGrid()) {
                finishablePuzzle = randomPuzzleBuilder(size, numFill, numX, numO);
            }
        } else {
            while (!finishablePuzzle.isFinishable(finishablePuzzle)) {
                finishablePuzzle = randomPuzzleBuilder(size, numFill, numX, numO);
            }
        }
        return finishablePuzzle;
    }

    public static MarupekeGrid randomPuzzleBuilder(int size, int numFill, int numX, int numO) throws Exception {
        Random random = new Random();
        MarupekeGrid puzzle = new MarupekeGrid(size);
        int i1 = 0, i2 = 0, i3 = 0;
        int trueSize = puzzle.marupekeTiles.length;
        if (numFill + numX + numO > ((trueSize * trueSize) / 2)) {
            throw new Exception("TooManyMarkedSquares");
        }
        while (i1 < numFill) {
            int x = random.nextInt(trueSize);
            int y = random.nextInt(trueSize);
            if (puzzle.marupekeTiles[x][y].isEditable()) {
                puzzle.setSolid(x, y);
                i1++;
            }
        }
        while (i2 < numX) {
            int x = random.nextInt(trueSize);
            int y = random.nextInt(trueSize);
            if (puzzle.marupekeTiles[x][y].isEditable()) {
                puzzle.setX(x, y, false);
                i2++;
            }
        }
        while (i3 < numO) {
            int x = random.nextInt(trueSize);
            int y = random.nextInt(trueSize);
            if (puzzle.marupekeTiles[x][y].isEditable()) {
                puzzle.setO(x, y, false);
                i3++;
            }
        }
        return puzzle;
    }

    @Override
    public String toString() {
        StringBuilder sBuild = new StringBuilder();
        for (MarupekeTile[] row : marupekeTiles) {
            for (MarupekeTile c : row) {
                sBuild.append(c.getGrid().getSymbol());
            }
            sBuild.append("\n");
        }
        return sBuild.toString();
    }

    public boolean isLegalGrid() {
        for (int i = 1; i < marupekeTiles.length - 1; i++) {
            for (int j = 1; j < marupekeTiles.length - 1; j++) {
                if (!checkSurroundingGrid(i, j)) {
                    countIllegalities(i, j);
                }
            }
        }
        for (int i = 1; i < marupekeTiles.length - 1; i++) {
            for (int j = 1; j < marupekeTiles.length - 1; j++) {
                if (!checkSurroundingGrid(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkSurroundingGrid(int i, int j) {
        if (marupekeTiles[i][j].getGrid().getSymbol() != '_' && marupekeTiles[i][j].getGrid().getSymbol() != '#') {
            //horizontal line
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i][j - 1].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i][j + 1].getGrid().getSymbol()) {
                return false;
                //vertical line
            }
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i - 1][j].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i + 1][j].getGrid().getSymbol()) {
                return false;
                //top left to bottom right diagonal
            }
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i - 1][j - 1].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i + 1][j + 1].getGrid().getSymbol()) {
                return false;
                //bottom left to top right diagonal
            }
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i + 1][j - 1].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i - 1][j + 1].getGrid().getSymbol()) {
                return false;
            }
            //left line
        }
        if (marupekeTiles[i][j - 1].getGrid().getSymbol() == marupekeTiles[i - 1][j - 1].getGrid().getSymbol() &&
                marupekeTiles[i][j - 1].getGrid().getSymbol() == marupekeTiles[i + 1][j - 1].getGrid().getSymbol()) {
            if (marupekeTiles[i][j - 1].getGrid().getSymbol() != '_' && marupekeTiles[i][j - 1].getGrid().getSymbol() != '#') {
                return false;
            }
            //right line
        }
        if (marupekeTiles[i][j + 1].getGrid().getSymbol() == marupekeTiles[i - 1][j + 1].getGrid().getSymbol() &&
                marupekeTiles[i][j + 1].getGrid().getSymbol() == marupekeTiles[i + 1][j + 1].getGrid().getSymbol()) {
            if (marupekeTiles[i][j + 1].getGrid().getSymbol() != '_' && marupekeTiles[i][j + 1].getGrid().getSymbol() != '#') {
                return false;
            }
            //top line
        }
        if (marupekeTiles[i - 1][j].getGrid().getSymbol() == marupekeTiles[i - 1][j - 1].getGrid().getSymbol() &&
                marupekeTiles[i - 1][j].getGrid().getSymbol() == marupekeTiles[i - 1][j + 1].getGrid().getSymbol()) {
            if (marupekeTiles[i - 1][j].getGrid().getSymbol() != '_' && marupekeTiles[i - 1][j].getGrid().getSymbol() != '#') {
                return false;
            }
            //bottom line
        }
        if (marupekeTiles[i + 1][j].getGrid().getSymbol() == marupekeTiles[i + 1][j - 1].getGrid().getSymbol() &&
                marupekeTiles[i + 1][j].getGrid().getSymbol() == marupekeTiles[i + 1][j + 1].getGrid().getSymbol()) {
            if (marupekeTiles[i + 1][j].getGrid().getSymbol() != '_' && marupekeTiles[i + 1][j].getGrid().getSymbol() != '#') {
                return false;
            }
        }
        return true;
    }

    public List<Reason> illegalitiesInGrid() {
        isLegalGrid(); // to countIllegalities in case this method is called before isLegalGrid()
        List<Reason> newList1 = new LinkedList<>();
        List<String> newList2 = new LinkedList<>();
        for (Reason reason : reasonList) {
            if (!newList1.contains(reason) && !newList2.contains(reason.toString())) {
                newList1.add(reason);
                newList2.add(reason.toString());
            }
        }
        reasonList = newList1;
        // reasonList shows the rows and columns for the error starting at
        // index 1 instead of 0 to be more user friendly
        return reasonList;
    }

    public void countIllegalities(int i, int j) {
        if (marupekeTiles[i][j].getGrid().getSymbol() != '_' && marupekeTiles[i][j].getGrid().getSymbol() != '#') {
            //horizontal line
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i][j - 1].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i][j + 1].getGrid().getSymbol()) {
                reasonList.add(new ConsecutiveHorizontal(i, j - 1, i, j + 1, marupekeTiles[i][j].getGrid()));
                //vertical line
            }
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i - 1][j].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i + 1][j].getGrid().getSymbol()) {
                reasonList.add(new ConsecutiveVertical(i - 1, j, i + 1, j, marupekeTiles[i][j].getGrid()));
                //top left to bottom right diagonal
            }
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i - 1][j - 1].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i + 1][j + 1].getGrid().getSymbol()) {
                reasonList.add(new ConsecutiveDiagonal(i - 1, j - 1, i + 1, j + 1, marupekeTiles[i][j].getGrid()));
                //bottom left to top right diagonal
            }
            if (marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i + 1][j - 1].getGrid().getSymbol() &&
                    marupekeTiles[i][j].getGrid().getSymbol() == marupekeTiles[i - 1][j + 1].getGrid().getSymbol()) {
                reasonList.add(new ConsecutiveDiagonal(i + 1, j - 1, i - 1, j + 1, marupekeTiles[i][j].getGrid()));
            }
            //left line
        }
        if (marupekeTiles[i][j - 1].getGrid().getSymbol() == marupekeTiles[i - 1][j - 1].getGrid().getSymbol() &&
                marupekeTiles[i][j - 1].getGrid().getSymbol() == marupekeTiles[i + 1][j - 1].getGrid().getSymbol()) {
            if (marupekeTiles[i][j - 1].getGrid().getSymbol() != '_' && marupekeTiles[i][j - 1].getGrid().getSymbol() != '#') {
                reasonList.add(new ConsecutiveVertical(i - 1, j - 1, i + 1, j - 1, marupekeTiles[i][j - 1].getGrid()));
            }
            //right line
        }
        if (marupekeTiles[i][j + 1].getGrid().getSymbol() == marupekeTiles[i - 1][j + 1].getGrid().getSymbol() &&
                marupekeTiles[i][j + 1].getGrid().getSymbol() == marupekeTiles[i + 1][j + 1].getGrid().getSymbol()) {
            if (marupekeTiles[i][j + 1].getGrid().getSymbol() != '_' && marupekeTiles[i][j + 1].getGrid().getSymbol() != '#') {
                reasonList.add(new ConsecutiveVertical(i - 1, j + 1, i + 1, j + 1, marupekeTiles[i][j + 1].getGrid()));
            }
            //top line
        }
        if (marupekeTiles[i - 1][j].getGrid().getSymbol() == marupekeTiles[i - 1][j - 1].getGrid().getSymbol() &&
                marupekeTiles[i - 1][j].getGrid().getSymbol() == marupekeTiles[i - 1][j + 1].getGrid().getSymbol()) {
            if (marupekeTiles[i - 1][j].getGrid().getSymbol() != '_' && marupekeTiles[i - 1][j].getGrid().getSymbol() != '#') {
                reasonList.add(new ConsecutiveHorizontal(i - 1, j - 1, i + 1, j + 1, marupekeTiles[i - 1][j].getGrid()));
            }
            //bottom line
        }
        if (marupekeTiles[i + 1][j].getGrid().getSymbol() == marupekeTiles[i + 1][j - 1].getGrid().getSymbol() &&
                marupekeTiles[i + 1][j].getGrid().getSymbol() == marupekeTiles[i + 1][j + 1].getGrid().getSymbol()) {
            if (marupekeTiles[i + 1][j].getGrid().getSymbol() != '_' && marupekeTiles[i + 1][j].getGrid().getSymbol() != '#') {
                reasonList.add(new ConsecutiveHorizontal(i + 1, j - 1, i + 1, j + 1, marupekeTiles[i + 1][j].getGrid()));
            }
        }
    }

    public void markX(int x, int y) {
        if (marupekeTiles[x][y].isEditable()) {
            setX(x, y, true);
        }
    }

    public void markO(int x, int y) {
        if (marupekeTiles[x][y].isEditable()) {
            setO(x, y, true);
        }
    }

    public boolean unMark(int x, int y) {
        if (marupekeTiles[x][y].isEditable()) {
            marupekeTiles[x][y].setGrid(MarupekeTile.Grid.BLANK);
            marupekeTiles[x][y].setEditable(true);
            return true;
        }
        return false;
    }

    public boolean isPuzzleComplete() {
        if (isLegalGrid()) {
            for (MarupekeTile[] marupekeTile : marupekeTiles) {
                for (int j = 0; j < marupekeTiles.length; j++) {
                    if (marupekeTile[j].getGrid() == MarupekeTile.Grid.BLANK) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public MarupekeGrid solvePuzzle(MarupekeGrid grid) {
        Stack<MarupekeGrid> toDo = new Stack<>();
        LinkedList<MarupekeGrid> seenAlready = new LinkedList<>();
        toDo.add(grid);
        while (!toDo.isEmpty()) {
            MarupekeGrid x = toDo.pop();
            if (x.isPuzzleComplete()) {
                return x;
            } else {
                seenAlready.add(x);
                if (!x.isLegalGrid()) {
                    continue;
                }
                LinkedList<MarupekeGrid> tempList = x.getChildren();
                for (MarupekeGrid y : tempList) {
                    if (!toDo.contains(y) && !seenAlready.contains(y)) {
                        toDo.add(y);
                    }
                }
            }
        }
        return null;
    }

    public LinkedList<MarupekeGrid> getChildren() {
        LinkedList<MarupekeGrid> marupekeGridCollection = new LinkedList<>();
        boolean xTurn = true;
        for (int x = 0; x < marupekeTiles.length; x++) {
            for (int y = 0; y < marupekeTiles.length; y++) {
                if (marupekeTiles[x][y].isEditable()) {
                    MarupekeTile[][] currentMarupekeTiles = new MarupekeTile[marupekeTiles.length][marupekeTiles.length];
                    for (int i = 0; i < marupekeTiles.length; i++) {
                        System.arraycopy(marupekeTiles[i], 0, currentMarupekeTiles[i], 0, marupekeTiles.length);
                    }
                    MarupekeTile[][] currentMarupekeTiles2 = new MarupekeTile[marupekeTiles.length][marupekeTiles.length];
                    for (int i = 0; i < marupekeTiles.length; i++) {
                        System.arraycopy(marupekeTiles[i], 0, currentMarupekeTiles2[i], 0, marupekeTiles.length);
                    }
                    MarupekeGrid currentState = new MarupekeGrid(marupekeTiles.length);
                    currentState.setMarupekeTiles(currentMarupekeTiles);
                    MarupekeGrid currentState2 = new MarupekeGrid(marupekeTiles.length);
                    currentState2.setMarupekeTiles(currentMarupekeTiles2);
                    if (xTurn) {
                        currentState.markX(x, y);
                        marupekeGridCollection.add(currentState);
                        xTurn = false;
                    } else {
                        currentState2.markO(x, y);
                        marupekeGridCollection.add(currentState2);
                        xTurn = true;
                    }
                }
            }
        }
        return marupekeGridCollection;
    }

    public void setMarupekeTiles(MarupekeTile[][] marupekeTiles) {
        this.marupekeTiles = marupekeTiles;
    }

    public boolean isFinishable(MarupekeGrid grid) {
        if (!grid.isLegalGrid()) {
            return false;
        }
        if (grid.solvePuzzle(grid) != null) {
            for (MarupekeTile[] marupekeTile : grid.marupekeTiles) {
                for (int j = 0; j < grid.marupekeTiles.length; j++) {
                    if (marupekeTile[j].isEditable()) {
                        marupekeTile[j].setGrid(MarupekeTile.Grid.BLANK);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public MarupekeTile[][] getMarupekeTiles() {
        return marupekeTiles;
    }

    public char getSquareSymbol(int x, int y) {
        return marupekeTiles[x][y].getGrid().getSymbol();
    }

    public boolean isTileEditable(int x, int y) {
        return marupekeTiles[x][y].isEditable();
    }

    public boolean isFilled() {
        for (MarupekeTile[] marupekeTile : marupekeTiles) {
            for (int j = 0; j < marupekeTiles.length; j++) {
                if (marupekeTile[j].getGrid() == MarupekeTile.Grid.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
}