import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableClusterTest {
    Variable variableA = new Variable('A');
    Variable variableB = new Variable('B', true);
    VariableCluster testVariableCluster = new VariableCluster(variableA, variableB);
    @Test
    public void testSize() {
        assertEquals(2, this.testVariableCluster.size());
    }
    @Test
    public void testGetVariableSymbols() {
        ArrayList<Character> variableSymbols = new ArrayList<>();
        variableSymbols.add('A');
        variableSymbols.add('B');
        assertEquals(variableSymbols, this.testVariableCluster.getVariableSymbols());
    }
}
