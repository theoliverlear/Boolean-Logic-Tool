package org.theoliverlear.model;
//=================================-Imports-==================================
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class BinaryRow {
    //============================-Variables-=================================
    List<Binary> binaryRow;
    //===========================-Constructors-===============================
    public BinaryRow() {
        this.binaryRow = new ArrayList<>();
    }
    public BinaryRow(List<Binary> binaryRow) {
        this.binaryRow = binaryRow;
    }
    //=============================-Methods-==================================

    public BinaryRow(Binary... binaries) {
        this.binaryRow = new ArrayList<>();
        Collections.addAll(this.binaryRow, binaries);
    }
    public Binary rowAnd() {
        for (Binary binaryDigit : this.binaryRow) {
            if (binaryDigit.equals(Binary.ZERO)) {
                return Binary.ZERO;
            }
        }
        return Binary.ONE;
    }
    public Binary rowOr() {
        for (Binary binaryDigit : this.binaryRow) {
            if (binaryDigit.equals(Binary.ONE)) {
                return Binary.ONE;
            }
        }
        return Binary.ZERO;
    }
    public Binary getBinaryRowDigit(int index) {
        return this.binaryRow.get(index);
    }
    public int size() {
        return this.binaryRow.size();
    }
    @Override
    public String toString() {
        String rowString = "";
        for (Binary binaryDigit : this.binaryRow) {
            rowString += binaryDigit.getBinary();
        }
        return rowString;
    }
    public void setBinaryRow(ArrayList<Binary> binaryRow) {
        this.binaryRow = binaryRow;
    }
    public void addBinary(Binary binary) {
        this.binaryRow.add(binary);
    }
    public void addBinaryRow(ArrayList<Binary> binaryRow) {
        this.binaryRow.addAll(binaryRow);
    }
}
