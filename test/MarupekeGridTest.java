import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class MarupekeGridTest {

    private MarupekeGrid marupekeGrid;

    @Before
    public void setUp() {
        marupekeGrid = new MarupekeGrid(5);
    }

    @Test
    public void testConstructorInitialGrid() {
        // \u0000 is the default value for the char primitive type
        assertEquals('_', marupekeGrid.getSquareSymbol(3, 3));
    }

    @Test
    public void testConstructorInitialEditable() {
        assertTrue(marupekeGrid.getMarupekeTiles()[3][3].isEditable());
    }

    @Test
    public void testConstructorMarupekeTilesSize() {
        assertEquals(5, marupekeGrid.getMarupekeTiles().length);
    }

    @Test
    public void testConstructorLargeSize() {
        MarupekeGrid largeMarupekeGrid = new MarupekeGrid(11);
        assertEquals(10, largeMarupekeGrid.getMarupekeTiles().length);
    }

    @Test
    public void testConstructorSmallSize() {
        MarupekeGrid largeMarupekeGrid = new MarupekeGrid(2);
        assertEquals(3, largeMarupekeGrid.getMarupekeTiles().length);
    }

    @Test
    public void testSetSolidReturnTrue() {
        assertTrue(marupekeGrid.setSolid(4, 4));
    }

    @Test
    public void testSetSolidReturnFalse() {
        marupekeGrid.getMarupekeTiles()[4][4].setEditable(false);
        assertFalse(marupekeGrid.setSolid(4, 4));
    }

    @Test
    public void testSetSolidSpecificData() {
        marupekeGrid.setSolid(0, 0);
        assertEquals('#', marupekeGrid.getSquareSymbol(0, 0));
    }

    @Test
    public void testSetSolidAllData() {
        boolean valuesCorrect = true;
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                marupekeGrid.setSolid(i, j);
            }
        }
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                if (marupekeGrid.getSquareSymbol(i, j) != '#') {
                    valuesCorrect = false;
                }
            }
        }
        assertTrue(valuesCorrect);
    }

    @Test
    public void testSetXReturnTrue() {
        assertTrue(marupekeGrid.setX(4, 4, false));
    }

    @Test
    public void testSetXReturnFalse() {
        marupekeGrid.getMarupekeTiles()[4][4].setEditable(false);
        assertFalse(marupekeGrid.setX(4, 4, false));
    }

    @Test
    public void testSetXSpecificData() {
        marupekeGrid.setX(0, 0, false);
        assertEquals('X', marupekeGrid.getSquareSymbol(0, 0));
    }

    @Test
    public void testSetXAllData() {
        boolean valuesCorrect = true;
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                marupekeGrid.setX(i, j, false);
            }
        }
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                if (marupekeGrid.getSquareSymbol(i, j) != 'X') {
                    valuesCorrect = false;
                }
            }
        }
        assertTrue(valuesCorrect);
    }

    @Test
    public void testSetOReturnTrue() {
        assertTrue(marupekeGrid.setO(4, 4, false));
    }

    @Test
    public void testSetOReturnFalse() {
        marupekeGrid.getMarupekeTiles()[4][4].setEditable(false);
        assertFalse(marupekeGrid.setO(4, 4, false));
    }

    @Test
    public void testSetOSpecificData() {
        marupekeGrid.setO(0, 0, false);
        assertEquals('O', marupekeGrid.getSquareSymbol(0, 0));
    }

    @Test
    public void testSetOAllData() {
        boolean valuesCorrect = true;
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                marupekeGrid.setO(i, j, false);
            }
        }
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                if (marupekeGrid.getSquareSymbol(i, j) != 'O') {
                    valuesCorrect = false;
                }
            }
        }
        assertTrue(valuesCorrect);
    }

    @Test
    public void testToStringRandom() {
        marupekeGrid.setO(2, 3, false);
        marupekeGrid.setX(1, 3, false);
        marupekeGrid.setX(4, 4, false);
        marupekeGrid.setO(0, 0, false);
        marupekeGrid.setX(0, 1, false);
        marupekeGrid.setX(0, 2, false);
        marupekeGrid.setX(0, 4, false);
        marupekeGrid.setSolid(0, 3);
        marupekeGrid.setO(4, 0, false);
        marupekeGrid.setO(4, 1, false);
        marupekeGrid.setO(4, 2, false);
        marupekeGrid.setO(4, 3, false);
        String output = "OXX#X\n" + "___X_\n" + "___O_\n" + "_____\n" + "OOOOX\n";
        assertEquals(output, marupekeGrid.toString());
    }

    @Test
    public void testToStringOuter1() {
        marupekeGrid.setSolid(0, 0);
        marupekeGrid.setSolid(0, 1);
        marupekeGrid.setSolid(0, 2);
        marupekeGrid.setSolid(0, 3);
        marupekeGrid.setSolid(0, 4);
        marupekeGrid.setO(4, 0, false);
        marupekeGrid.setO(4, 1, false);
        marupekeGrid.setO(4, 2, false);
        marupekeGrid.setO(4, 3, false);
        marupekeGrid.setO(4, 4, false);
        marupekeGrid.setX(1, 0, false);
        marupekeGrid.setX(2, 0, false);
        marupekeGrid.setX(3, 0, false);
        marupekeGrid.setX(1, 4, false);
        marupekeGrid.setX(2, 4, false);
        marupekeGrid.setX(3, 4, false);
        String output = "#####\n" + "X___X\n" + "X___X\n" + "X___X\n" + "OOOOO\n";
        assertEquals(output, marupekeGrid.toString());
    }

    @Test
    public void testToStringOuter2() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(0, 1, false);
        marupekeGrid.setX(0, 2, false);
        marupekeGrid.setX(0, 3, false);
        marupekeGrid.setX(0, 4, false);
        marupekeGrid.setO(4, 0, false);
        marupekeGrid.setO(4, 1, false);
        marupekeGrid.setO(4, 2, false);
        marupekeGrid.setO(4, 3, false);
        marupekeGrid.setO(4, 4, false);
        marupekeGrid.setSolid(1, 0);
        marupekeGrid.setSolid(2, 0);
        marupekeGrid.setSolid(3, 0);
        marupekeGrid.setSolid(1, 4);
        marupekeGrid.setSolid(2, 4);
        marupekeGrid.setSolid(3, 4);
        String output = "XXXXX\n" + "#___#\n" + "#___#\n" + "#___#\n" + "OOOOO\n";
        assertEquals(output, marupekeGrid.toString());
    }

    @Test
    public void testToStringInner() {
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(1, 2);
        marupekeGrid.setSolid(1, 3);
        marupekeGrid.setX(2, 1, false);
        marupekeGrid.setX(2, 2, false);
        marupekeGrid.setX(2, 3, false);
        marupekeGrid.setO(3, 1, false);
        marupekeGrid.setO(3, 2, false);
        marupekeGrid.setO(3, 3, false);
        String output = "_____\n" + "_###_\n" + "_XXX_\n" + "_OOO_\n" + "_____\n";
        assertEquals(output, marupekeGrid.toString());
    }

    @Test
    public void testRandomPuzzleBuilderSize() throws Exception {
        MarupekeGrid randomMarupekeGrid = MarupekeGrid.randomPuzzle(5, 6, 3, 2);
        assertEquals(5, randomMarupekeGrid.getMarupekeTiles().length);
    }

    @Test
    public void testRandomPuzzleBuilderNumFill() throws Exception {
        MarupekeGrid randomMarupekeGrid = MarupekeGrid.randomPuzzle(9, 30, 5, 5);
        int totalFill = 0;
        for (int i = 0; i < randomMarupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < randomMarupekeGrid.getMarupekeTiles().length; j++) {
                if (randomMarupekeGrid.getSquareSymbol(i, j) == '#') {
                    totalFill++;
                }
            }
        }
        assertEquals(30, totalFill);
    }

    @Test
    public void testRandomPuzzleBuilderNumX() throws Exception {
        MarupekeGrid randomMarupekeGrid = MarupekeGrid.randomPuzzle(7, 2, 9, 4);
        int totalX = 0;
        for (int i = 0; i < randomMarupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < randomMarupekeGrid.getMarupekeTiles().length; j++) {
                if (randomMarupekeGrid.getSquareSymbol(i, j) == 'X') {
                    totalX++;
                }
            }
        }
        assertEquals(9, totalX);
    }

    @Test
    public void testRandomPuzzleBuilderNumO() throws Exception {
        MarupekeGrid randomMarupekeGrid = MarupekeGrid.randomPuzzle(4, 1, 3, 2);
        int totalO = 0;
        for (int i = 0; i < randomMarupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < randomMarupekeGrid.getMarupekeTiles().length; j++) {
                if (randomMarupekeGrid.getSquareSymbol(i, j) == 'O') {
                    totalO++;
                }
            }
        }
        assertEquals(2, totalO);
    }

    @Test
    public void testIsLegalTrue() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(1, 0, false);
        marupekeGrid.setO(2, 0, false);
        marupekeGrid.setO(0, 4, false);
        marupekeGrid.setO(1, 4, false);
        marupekeGrid.setX(2, 4, false);
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(1, 2);
        marupekeGrid.setSolid(1, 3);
        marupekeGrid.setX(2, 1, false);
        marupekeGrid.setO(2, 2, false);
        marupekeGrid.setX(2, 3, false);
        marupekeGrid.setO(3, 1, false);
        marupekeGrid.setO(3, 3, false);
        marupekeGrid.setX(4, 2, false);
        // The above in string form will be:
        // "X___O\n" + "X###O\n" + "OXOXX\n" + "_O_O_\n" + "__X__\n"
        assertTrue(marupekeGrid.isLegalGrid());
    }

    @Test
    public void testIsLegalFalseHorizontal() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(1, 0, false);
        marupekeGrid.setO(2, 0, false);
        marupekeGrid.setO(0, 4, false);
        marupekeGrid.setO(1, 4, false);
        marupekeGrid.setX(2, 4, false);
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(1, 2);
        marupekeGrid.setSolid(1, 3);
        marupekeGrid.setX(2, 1, false);
        marupekeGrid.setO(2, 2, false);
        marupekeGrid.setX(2, 3, false);
        marupekeGrid.setO(3, 1, false);
        marupekeGrid.setO(4, 2, false);
        marupekeGrid.setO(3, 3, false);
        // The above in string form will be:
        // "X___O\n" + "X###O\n" + "OXOXX\n" + "_OOO_\n" + "_____\n"
        assertFalse(marupekeGrid.isLegalGrid());
    }

    @Test
    public void testIsLegalFalseVertical() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(1, 0, false);
        marupekeGrid.setX(2, 0, false);
        marupekeGrid.setO(0, 4, false);
        marupekeGrid.setO(1, 4, false);
        marupekeGrid.setX(2, 4, false);
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(1, 2);
        marupekeGrid.setSolid(1, 3);
        marupekeGrid.setX(2, 1, false);
        marupekeGrid.setO(2, 2, false);
        marupekeGrid.setX(2, 3, false);
        marupekeGrid.setO(3, 1, false);
        marupekeGrid.setO(3, 3, false);
        marupekeGrid.setX(4, 2, false);
        // The above in string form will be:
        // "X___O\n" + "X###O\n" + "XXOXX\n" + "_O_O_\n" + "__X__\n"
        assertFalse(marupekeGrid.isLegalGrid());
    }

    @Test
    public void testIsLegalFalseDiagonal() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(1, 0, false);
        marupekeGrid.setO(2, 0, false);
        marupekeGrid.setO(0, 4, false);
        marupekeGrid.setO(1, 4, false);
        marupekeGrid.setX(2, 4, false);
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(1, 2);
        marupekeGrid.setSolid(1, 3);
        marupekeGrid.setX(2, 1, false);
        marupekeGrid.setO(2, 2, false);
        marupekeGrid.setX(2, 3, false);
        marupekeGrid.setO(3, 1, false);
        marupekeGrid.setX(3, 2, false);
        marupekeGrid.setO(3, 3, false);
        // The above in string form will be:
        // "X___O\n" + "X###O\n" + "OXOXX\n" + "_OXO_\n" + "_____\n"
        assertFalse(marupekeGrid.isLegalGrid());
    }

    @Test
    public void illegalitiesInGridHorizontalAndX() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(0, 1, false);
        marupekeGrid.setX(0, 2, false);
        List<Reason> newList = new LinkedList<>();
        newList.add(new ConsecutiveHorizontal(0, 0, 0, 2, MarupekeTile.Grid.CROSS));
        assertEquals(marupekeGrid.illegalitiesInGrid().toString(), newList.toString());
    }

    @Test
    public void illegalitiesInGridVerticalAndO() {
        marupekeGrid.setO(0, 0, false);
        marupekeGrid.setO(1, 0, false);
        marupekeGrid.setO(2, 0, false);
        List<Reason> newList = new LinkedList<>();
        newList.add(new ConsecutiveVertical(0, 0, 2, 0, MarupekeTile.Grid.CIRCLE));
        assertEquals(marupekeGrid.illegalitiesInGrid().toString(), newList.toString());
    }

    @Test
    public void illegalitiesInGridDiagonalBoth() {
        marupekeGrid.setO(0, 0, false);
        marupekeGrid.setO(1, 1, false);
        marupekeGrid.setO(2, 2, false);
        marupekeGrid.setX(2, 4, false);
        marupekeGrid.setX(3, 3, false);
        marupekeGrid.setX(4, 2, false);
        List<Reason> newList = new LinkedList<>();
        newList.add(new ConsecutiveDiagonal(0, 0, 2, 2, MarupekeTile.Grid.CIRCLE));
        newList.add(new ConsecutiveDiagonal(4, 2, 2, 4, MarupekeTile.Grid.CROSS));
        assertEquals(marupekeGrid.illegalitiesInGrid().toString(), newList.toString());
    }

    @Test
    public void illegalitiesInGridIgnoreSolids() {
        marupekeGrid.setSolid(0, 0);
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(2, 2);
        marupekeGrid.setSolid(2, 4);
        marupekeGrid.setSolid(3, 3);
        marupekeGrid.setSolid(4, 2);
        marupekeGrid.setSolid(0, 2);
        marupekeGrid.setSolid(0, 3);
        marupekeGrid.setSolid(0, 4);
        marupekeGrid.setSolid(2, 0);
        marupekeGrid.setSolid(3, 0);
        marupekeGrid.setSolid(4, 0);
        assertTrue(marupekeGrid.illegalitiesInGrid().isEmpty());
    }

    @Test
    public void testRandomPuzzleLegal() throws Exception {
        MarupekeGrid puzzle = MarupekeGrid.randomPuzzle(10,10,20,20);
        assertTrue(puzzle.isLegalGrid());
    }

    @Test
    public void testRandomPuzzleLegal2() throws Exception {
        MarupekeGrid puzzle = MarupekeGrid.randomPuzzle(3,1,1,2);
        assertTrue(puzzle.isLegalGrid());
    }

    @Test(expected = Exception.class)
    public void testRandomPuzzleIllegal1() throws Exception {
        MarupekeGrid.randomPuzzle(10,11,20,20);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testRandomPuzzleIllegal2() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("TooManyMarkedSquares");
        MarupekeGrid.randomPuzzle(6,5,7,7);
    }

    @Test
    public void testMarkXLowBoundary() {
        marupekeGrid.markX(0, 0);
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), 'X');
        marupekeGrid.setSolid(0, 0);
        marupekeGrid.markX(0, 0);
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), '#');

    }

    @Test
    public void testMarkXHighBoundary() {
        marupekeGrid.markX(4, 4);
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), 'X');
        marupekeGrid.setO(4, 4, false);
        marupekeGrid.markX(4, 4);
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), 'O');
    }

    @Test
    public void testMarkOLowBoundary() {
        marupekeGrid.markO(0, 0);
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), 'O');
        marupekeGrid.setSolid(0, 0);
        marupekeGrid.markO(0, 0);
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), '#');
    }

    @Test
    public void testMarkOHighBoundary() {
        marupekeGrid.markO(4, 4);
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), 'O');
        marupekeGrid.setX(4, 4, false);
        marupekeGrid.markO(4, 4);
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), 'X');
    }

    @Test
    public void testUnMarkLowBoundary() {
        marupekeGrid.markO(0, 0);
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), 'O');
        assertTrue(marupekeGrid.unMark(0, 0));
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), '_');
        marupekeGrid.setSolid(0, 0);
        assertFalse(marupekeGrid.unMark(0, 0));
        assertEquals(marupekeGrid.getSquareSymbol(0, 0), '#');
    }

    @Test
    public void testUnMarkHighBoundary() {
        marupekeGrid.markO(4, 4);
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), 'O');
        assertTrue(marupekeGrid.unMark(4, 4));
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), '_');
        marupekeGrid.setSolid(4, 4);
        assertFalse(marupekeGrid.unMark(4, 4));
        assertEquals(marupekeGrid.getSquareSymbol(4, 4), '#');
    }

    @Test
    public void testIsPuzzleComplete() {
        marupekeGrid.setX(0, 0, false);
        marupekeGrid.setX(1, 0, false);
        marupekeGrid.setO(2, 0, false);
        marupekeGrid.setO(0, 4, false);
        marupekeGrid.setX(1, 4, false);
        assertTrue(marupekeGrid.isLegalGrid());
        assertFalse(marupekeGrid.isPuzzleComplete());
        marupekeGrid.setO(2, 4, false);
        marupekeGrid.setSolid(1, 1);
        marupekeGrid.setSolid(1, 2);
        marupekeGrid.setSolid(1, 3);
        marupekeGrid.setX(2, 1, false);
        assertTrue(marupekeGrid.isLegalGrid());
        assertFalse(marupekeGrid.isPuzzleComplete());
        marupekeGrid.setO(2, 2, false);
        marupekeGrid.setX(2, 3, false);
        marupekeGrid.setX(3, 1, false);
        marupekeGrid.setO(3, 2, false);
        marupekeGrid.setX(3, 3, false);
        assertTrue(marupekeGrid.isLegalGrid());
        assertFalse(marupekeGrid.isPuzzleComplete());
        marupekeGrid.setO(3, 4, false);
        marupekeGrid.setX(4, 4, false);
        marupekeGrid.setO(4, 3, false);
        marupekeGrid.setX(4, 2, false);
        marupekeGrid.setX(3, 0, false);
        assertTrue(marupekeGrid.isLegalGrid());
        assertFalse(marupekeGrid.isPuzzleComplete());
        marupekeGrid.setO(4, 1, false);
        marupekeGrid.setX(4, 0, false);
        marupekeGrid.setO(0, 1, false);
        marupekeGrid.setX(0, 2, false);
        assertTrue(marupekeGrid.isLegalGrid());
        assertFalse(marupekeGrid.isPuzzleComplete());
        marupekeGrid.setX(0, 3, false);
        assertTrue(marupekeGrid.isLegalGrid());
        assertTrue(marupekeGrid.isPuzzleComplete());
    }

    @Test
    public void testIsPuzzleCompleteIllegal() {
        marupekeGrid.setX(0,0,false);
        marupekeGrid.setX(0,1,false);
        marupekeGrid.setX(0,2,false);
        marupekeGrid.setX(0,3,false);
        marupekeGrid.setX(0,4,false);
        marupekeGrid.setX(1,0,false);
        marupekeGrid.setX(1,1,false);
        marupekeGrid.setX(1,2,false);
        marupekeGrid.setX(1,3,false);
        marupekeGrid.setX(1,4,false);
        marupekeGrid.setX(2,0,false);
        marupekeGrid.setX(2,1,false);
        marupekeGrid.setX(2,2,false);
        marupekeGrid.setX(2,3,false);
        marupekeGrid.setX(2,4,false);
        marupekeGrid.setX(3,0,false);
        marupekeGrid.setX(3,1,false);
        marupekeGrid.setX(3,2,false);
        marupekeGrid.setX(3,3,false);
        marupekeGrid.setX(3,4,false);
        marupekeGrid.setX(4,0,false);
        marupekeGrid.setX(4,1,false);
        marupekeGrid.setX(4,2,false);
        marupekeGrid.setX(4,3,false);
        marupekeGrid.setX(4,4,false);
        assertFalse(marupekeGrid.isPuzzleComplete());
    }
}