import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryRowTest {
    Binary variableA = Binary.ZERO;
    Binary variableB = Binary.ONE;
    Binary variableC = Binary.ZERO;

    BinaryRow testRow = new BinaryRow(variableA, variableB, variableC);
    @Test
    public void testSize() {
        assertEquals(3, this.testRow.size());
    }
    @Test
    public void testGetBinaryRowDigit() {
        assertEquals(Binary.ZERO, this.testRow.getBinaryRowDigit(0));
        assertEquals(Binary.ONE, this.testRow.getBinaryRowDigit(1));
        assertEquals(Binary.ZERO, this.testRow.getBinaryRowDigit(2));
    }
    @Test
    public void testRowAnd() {
        assertEquals(Binary.ZERO, this.testRow.rowAnd());
    }
    @Test
    public void testRowOr() {
        assertEquals(Binary.ONE, this.testRow.rowOr());
    }
}
