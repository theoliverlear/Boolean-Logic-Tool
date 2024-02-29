import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {
    Variable testVariable = new Variable('A');
    Variable testVariablePrime = new Variable('B', true);
    @Test
    public void testVariableSymbol() {
        assertEquals('A', this.testVariable.getVariableSymbol());
    }
    @Test
    public void testGetBinaryFromInput() {
        assertEquals(Binary.ZERO, this.testVariable.getBinaryFromInput());
        assertEquals(Binary.ONE, this.testVariablePrime.getBinaryFromInput());
    }
    @Test
    public void testGetBinaryFromInputWithInput() {
        assertEquals(Binary.ZERO, this.testVariable.getBinaryFromInput(Binary.ZERO));
        assertEquals(Binary.ONE, this.testVariable.getBinaryFromInput(Binary.ONE));
        assertEquals(Binary.ONE, this.testVariablePrime.getBinaryFromInput(Binary.ZERO));
        assertEquals(Binary.ZERO, this.testVariablePrime.getBinaryFromInput(Binary.ONE));
    }
    @Test
    public void testVariableIsPrime() {
        assertFalse(this.testVariable.getIsPrime());
        assertTrue(this.testVariablePrime.getIsPrime());
    }

}
