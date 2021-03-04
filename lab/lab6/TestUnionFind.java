import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {


    @Test
    public void testStuff() {
        UnionFind ut = new UnionFind(5);
        ut.connect(0, 1);
        ut.connect(2, 3);
        ut.connect(2, 4);
        assertEquals(ut.parent(4), 3);
        assertEquals(ut.sizeOf(3), 3);
        assertFalse(ut.isConnected(4, 0));
        assertEquals(ut.find(4), 3);
        ut.connect(1, 4);
        assertTrue(ut.isConnected(4, 0));
        assertEquals(ut.find(0), 3);
        assertEquals(ut.parent(1), 3);
    }

    @Test
    public void testCompression() {
        UnionFind ut = new UnionFind(6);
        ut.connect(0, 1);
        ut.connect(2, 3);
        ut.connect(2, 4);
        ut.connect(1, 4);
        ut.connect(0, 5);
        assertEquals(ut.parent(0), ut.parent(5));
    }
}
