package junit;

import org.junit.Test;
import org.theoliverlear.model.Binary;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTest {
    Binary testBinary = new Binary("1");
    @Test
    public void testEquals() {
        boolean result = testBinary.equals(Binary.ONE);
        assertTrue(result);

        boolean resultTestTwo = testBinary.equals(Binary.ZERO);
        assertFalse(resultTestTwo);
    }
    @Test
    public void testAnd() {
        Binary comparedBinary = new Binary("1");
        Binary result = testBinary.and(comparedBinary);
        assertEquals(Binary.ONE, result);

        Binary comparedBinaryTestTwo = new Binary("0");
        Binary resultTestTwo = testBinary.and(comparedBinaryTestTwo);
        assertEquals(Binary.ZERO, resultTestTwo);
    }
    @Test
    public void testVariableLengthAnd() {
        Binary comparedBinary = new Binary("1");
        Binary result = testBinary.and(comparedBinary, comparedBinary);
        assertEquals(Binary.ONE, result);

        Binary comparedBinaryTestTwo = new Binary("0");
        Binary resultTestTwo = testBinary.and(comparedBinary, comparedBinaryTestTwo);
        assertEquals(Binary.ZERO, resultTestTwo);
    }
}
