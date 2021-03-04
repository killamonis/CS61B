import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(2);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'c'));
        assertFalse(offByN.equalChars('a', 'z'));
        assertFalse(offByN.equalChars('&', '%'));
    }
}
