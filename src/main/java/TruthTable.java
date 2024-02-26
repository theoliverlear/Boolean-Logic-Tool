import java.util.ArrayList;

public class TruthTable {
    int numVariables;
    ArrayList<BinaryColumn> truthTable;
    public TruthTable(int numVariables) {
        this.numVariables = numVariables;
        this.truthTable = new ArrayList<>();
        this.generateDefaultTruthTable();
    }
    public void generateDefaultTruthTable() {
        for (int i = this.numVariables - 1; i >= 0; i--) {
            BinaryColumn binaryColumn = new BinaryColumn(i, this.numVariables);
            this.truthTable.add(binaryColumn);
        }
    }
    @Override
    public String toString() {
        String tableString = "";
        for (int i = 0; i < this.truthTable.get(0).size(); i++) {
            for (BinaryColumn binaryColumn : this.truthTable) {
                tableString += binaryColumn.getBinaryColumnDigit(i).getBinary();
            }
            tableString += "\n";
        }
        return tableString;
    }
    public static void main(String[] args) {
        TruthTable truthTable = new TruthTable(4);
        System.out.println(truthTable);
    }
}
