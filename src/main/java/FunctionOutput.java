import java.util.ArrayList;

public class FunctionOutput {
    ArrayList<Binary> functionOutput = new ArrayList<>();
    TruthTable truthTable;
    VariableCluster variableCluster;
    public FunctionOutput(VariableCluster variableCluster) {
        this.truthTable = new TruthTable(variableCluster.getVariableCluster().size());
        this.variableCluster = variableCluster;
        this.fetchFunctionOutput();
    }
    public FunctionOutput(BinaryRow binaryRow, VariableCluster variableCluster) {
        this.truthTable = new TruthTable(variableCluster.getVariableCluster().size());
        this.variableCluster = variableCluster;
        this.fetchFunctionOutput();
    }
    public Binary getFunctionOutput(BinaryRow binaryRow) {
        ArrayList<Variable> cluster = this.variableCluster.getVariableCluster();
        ArrayList<Binary> binaryRowList = new ArrayList<>();
        for (int i = 0; i < cluster.size(); i++) {
            Variable variable = cluster.get(i);
            Binary binaryRowDigit = binaryRow.getBinaryRowDigit(i);
            binaryRowList.add(variable.getBinaryFromInput(binaryRowDigit));
        }
        BinaryRow binaryRowOutput = new BinaryRow(binaryRowList);
        return binaryRowOutput.rowAnd();
    }
    public void fetchFunctionOutput() {
        ArrayList<Binary> binaryVariableRowOutput = new ArrayList<>();
        for (BinaryRow binaryRow : this.truthTable.getTruthTableRows()) {
            ArrayList<Variable> cluster = this.variableCluster.getVariableCluster();
            ArrayList<Binary> binaryRowList = new ArrayList<>();
            for (int i = 0; i < cluster.size(); i++) {
                Variable variable = cluster.get(i);
                Binary binaryRowDigit = binaryRow.getBinaryRowDigit(i);
                binaryRowList.add(variable.getBinaryFromInput(binaryRowDigit));
            }
            BinaryRow binaryRowOutput = new BinaryRow(binaryRowList);
            binaryVariableRowOutput.add(binaryRowOutput.rowAnd());
        }
        this.functionOutput = binaryVariableRowOutput;
    }
    public ArrayList<Binary> getFunctionOutput() {
        return this.functionOutput;
    }
    public void setFunctionOutput(ArrayList<Binary> functionOutput) {
        this.functionOutput = functionOutput;
    }
}
