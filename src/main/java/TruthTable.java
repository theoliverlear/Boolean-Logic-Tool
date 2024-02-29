//=================================-Imports-==================================
import java.util.ArrayList;

public class TruthTable {
    //============================-Variables-=================================
    int numVariables;
    ArrayList<BinaryColumn> truthTableColumns;
    ArrayList<BinaryRow> truthTableRows;
    BinaryColumn functionColumn;
    //===========================-Constructors-===============================
    public TruthTable(int numVariables) {
        this.numVariables = numVariables;
        this.truthTableColumns = new ArrayList<>();
        this.truthTableRows = new ArrayList<>();
        this.generateDefaultTruthTable();
        this.generateTruthTableRows();
    }
    public TruthTable(int numVariables, BinaryColumn functionColumn) {
        this.numVariables = numVariables;
        this.functionColumn = functionColumn;
        this.truthTableColumns = new ArrayList<>();
        this.truthTableRows = new ArrayList<>();
        this.generateDefaultTruthTable();
        this.generateTruthTableRows();
    }
    //=============================-Methods-==================================
    public void generateDefaultTruthTable() {
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
                ArrayList<BinaryColumn> tableColumns = this.truthTableColumns;
                for (int j = 0; j < tableColumns.size(); j++) {
                    BinaryColumn binaryColumn = tableColumns.get(j);
                    tableString += binaryColumn.getBinaryColumnDigit(i).getBinary();
                }
                tableString += "|" + this.functionColumn.getBinaryColumnDigit(i)+ "\n";
            }
        }
        return tableString;
    }

    public ArrayList<BinaryRow> getTruthTableRows() {
        return this.truthTableRows;
    }
    public void setTruthTableRows(ArrayList<BinaryRow> truthTableRows) {
        this.truthTableRows = truthTableRows;
    }
    public ArrayList<BinaryColumn> getTruthTableColumns() {
        return this.truthTableColumns;
    }
    public void setTruthTableColumns(ArrayList<BinaryColumn> truthTableColumns) {
        this.truthTableColumns = truthTableColumns;
    }
    public static void main(String[] args) {
        TruthTable truthTable = new TruthTable(4);
        System.out.println(truthTable);
        System.out.println();
        System.out.println(truthTable.truthTableRows);
        System.out.println();
    }
}
