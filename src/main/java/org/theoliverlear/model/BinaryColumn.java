package org.theoliverlear.model;
//=================================-Imports-==================================
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BinaryColumn {
    //============================-Variables-=================================
    int numVariables;
    int positionFromLast;
    List<Binary> binaryColumn;
    //===========================-Constructors-===============================
    public BinaryColumn(List<Binary> binaryColumn) {
        this.binaryColumn = binaryColumn;
    }
    public BinaryColumn(int positionFromLast, int numVariables) {
        this.positionFromLast = positionFromLast;
        this.numVariables = numVariables;
        this.binaryColumn = new ArrayList<>();
        this.fetchTruthTableColumn();
    }
    //=============================-Methods-==================================
    public void fetchTruthTableColumn() {
        List<Binary> binaryColumn = new ArrayList<>();
        int numCombinations = (int) Math.pow(2, this.numVariables);
        int numChangesAmount = (int) Math.pow(2, this.positionFromLast);
        int rowPrintCount = 0;
        Binary currentNum = Binary.ZERO;
        int currentNumCount = 0;
        while (rowPrintCount < numCombinations) {
            if (currentNumCount == numChangesAmount) {
                if (currentNum.equals(Binary.ZERO)) {
                    currentNum = Binary.ONE;
                } else {
                    currentNum = Binary.ZERO;
                }
                currentNumCount = 0;
            }
            binaryColumn.add(currentNum);
            currentNumCount++;
            rowPrintCount++;
        }
        this.binaryColumn = binaryColumn;
    }
    public int size() {
        return this.binaryColumn.size();
    }
    public Binary getBinaryColumnDigit(int index) {
        return this.binaryColumn.get(index);
    }
    @Override
    public String toString() {
        String binaryColumnString = "";
        for (Binary binaryDigit : this.binaryColumn) {
            binaryColumnString += binaryDigit.getBinary();
        }
        return binaryColumnString;
    }
}
