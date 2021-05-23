import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarupekeTileTest {

    private MarupekeTile marupekeTile;

    @Before
    public void setUp(){
        marupekeTile = new MarupekeTile();
    }

    @Test
    public void testInitialEditable(){
        assertTrue(marupekeTile.isEditable());
    }

    @Test
    public void testInitialGrid(){
        assertEquals(marupekeTile.getGrid(), MarupekeTile.Grid.BLANK);
    }

    @Test
    public void testChangeEditable(){
        marupekeTile.setEditable(false);
        assertFalse(marupekeTile.isEditable());
        marupekeTile.setEditable(true);
        assertTrue(marupekeTile.isEditable());
    }

    @Test
    public void testChangeGrid(){
        marupekeTile.setGrid(MarupekeTile.Grid.CROSS);
        assertEquals(marupekeTile.getGrid(), MarupekeTile.Grid.CROSS);
        marupekeTile.setGrid(MarupekeTile.Grid.CIRCLE);
        assertEquals(marupekeTile.getGrid(), MarupekeTile.Grid.CIRCLE);
        marupekeTile.setGrid(MarupekeTile.Grid.SOLID);
        assertEquals(marupekeTile.getGrid(), MarupekeTile.Grid.SOLID);
        marupekeTile.setGrid(MarupekeTile.Grid.BLANK);
        assertEquals(marupekeTile.getGrid(), MarupekeTile.Grid.BLANK);
    }

    @Test
    public void testGridSymbols(){
        marupekeTile.setGrid(MarupekeTile.Grid.CROSS);
        assertEquals(marupekeTile.getGrid().getSymbol(), 'X');
        marupekeTile.setGrid(MarupekeTile.Grid.CIRCLE);
        assertEquals(marupekeTile.getGrid().getSymbol(), 'O');
        marupekeTile.setGrid(MarupekeTile.Grid.SOLID);
        assertEquals(marupekeTile.getGrid().getSymbol(), '#');
        marupekeTile.setGrid(MarupekeTile.Grid.BLANK);
        assertEquals(marupekeTile.getGrid().getSymbol(), '_');

    }
}