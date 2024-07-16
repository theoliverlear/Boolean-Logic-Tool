package org.theoliverlear.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionOutput {
    List<Binary> functionOutput;
    TruthTable truthTable;
    VariableCluster variableCluster;
    public FunctionOutput(VariableCluster variableCluster) {
        this.functionOutput = new ArrayList<>();
        this.truthTable = new TruthTable(variableCluster.getVariableCluster().size());
        this.variableCluster = variableCluster;
        this.fetchFunctionOutput();
    }
    public FunctionOutput(BinaryRow binaryRow, VariableCluster variableCluster) {
        this.functionOutput = new ArrayList<>();
        this.truthTable = new TruthTable(variableCluster.getVariableCluster().size());
        this.variableCluster = variableCluster;
        this.fetchFunctionOutput();
    }
    public Binary getFunctionOutput(BinaryRow binaryRow) {
        List<Variable> cluster = this.variableCluster.getVariableCluster();
        List<Binary> binaryRowList = new ArrayList<>();
        for (int i = 0; i < cluster.size(); i++) {
            Variable variable = cluster.get(i);
            Binary binaryRowDigit = binaryRow.getBinaryRowDigit(i);
            binaryRowList.add(variable.getBinaryFromInput(binaryRowDigit));
        }
        BinaryRow binaryRowOutput = new BinaryRow(binaryRowList);
        return binaryRowOutput.rowAnd();
    }
    public void fetchFunctionOutput() {
        List<Binary> binaryVariableRowOutput = new ArrayList<>();
        for (BinaryRow binaryRow : this.truthTable.getTruthTableRows()) {
            List<Variable> cluster = this.variableCluster.getVariableCluster();
            List<Binary> binaryRowList = new ArrayList<>();
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
}
