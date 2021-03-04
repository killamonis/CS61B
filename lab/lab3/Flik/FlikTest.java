import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber(){
        assertTrue("128 should equal 128", Flik.isSameNumber(128, 128));
        assertFalse("128 should not equal 500", Flik.isSameNumber(128, 500));
    }
}
