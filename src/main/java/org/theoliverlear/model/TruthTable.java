package org.theoliverlear.model;
//=================================-Imports-==================================
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TruthTable {
    //============================-Variables-=================================
    int numVariables;
    List<BinaryColumn> truthTableColumns;
    List<BinaryRow> truthTableRows;
    BinaryColumn functionColumn;
    //===========================-Constructors-===============================
    public TruthTable(int numVariables) {
        this.numVariables = numVariables;
        this.truthTableColumns = new ArrayList<>();
        this.truthTableRows = new ArrayList<>();
        this.generateDefaultTruthTable();
    }
    public TruthTable(int numVariables, BinaryColumn functionColumn) {
        this.numVariables = numVariables;
        this.functionColumn = functionColumn;
        this.truthTableColumns = new ArrayList<>();
        this.truthTableRows = new ArrayList<>();
        this.generateDefaultTruthTable();
    }
    //=============================-Methods-==================================
    public void generateDefaultTruthTable() {
        this.generateTruthTableColumns();
        this.generateTruthTableRows();
    }
    public void generateTruthTableColumns() {
        for (int i = this.numVariables - 1; i >= 0; i--) {
            BinaryColumn binaryColumn = new BinaryColumn(i, this.numVariables);
            this.truthTableColumns.add(binaryColumn);
        }
    }
    public void generateTruthTableRows() {
        for (int i = 0; i < this.truthTableColumns.get(0).size(); i++) {
            ArrayList<Binary> binaryRowList = new ArrayList<>();
            for (int j = 0; j < this.truthTableColumns.size(); j++) {
                binaryRowList.add(this.truthTableColumns.get(j).getBinaryColumnDigit(i));
            }
            BinaryRow binaryRow = new BinaryRow(binaryRowList);
            this.truthTableRows.add(binaryRow);
        }
    }
    //============================-Overrides-=================================
    @Override
    public String toString() {
        String tableString = "";
        if (this.functionColumn == null) {
            for (int i = 0; i < this.truthTableColumns.get(0).size(); i++) {
                for (BinaryColumn binaryColumn : this.truthTableColumns) {
                    tableString += binaryColumn.getBinaryColumnDigit(i).getBinary();
                }
                tableString += "\n";
            }
        } else {
            for (int i = 0; i < this.truthTableColumns.get(0).size(); i++) {
                List<BinaryColumn> tableColumns = this.truthTableColumns;
                for (int j = 0; j < tableColumns.size(); j++) {
                    BinaryColumn binaryColumn = tableColumns.get(j);
                    tableString += binaryColumn.getBinaryColumnDigit(i).getBinary();
                }
                tableString += "|" + this.functionColumn.getBinaryColumnDigit(i)+ "\n";
            }
        }
        return tableString;
    }

    public static void main(String[] args) {
        TruthTable truthTable = new TruthTable(4);
        System.out.println(truthTable);
        System.out.println();
        System.out.println(truthTable.truthTableRows);
        System.out.println();
    }
}
