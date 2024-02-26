import java.util.ArrayList;

public class BinaryColumn {
    int numVariables;
    int positionFromLast;
    ArrayList<Binary> binaryColumn;
    public BinaryColumn(int positionFromLast, int numVariables) {
        this.positionFromLast = positionFromLast;
        this.numVariables = numVariables;
        this.binaryColumn = new ArrayList<>();
        this.fetchTruthTableColumn();
    }

    public void fetchTruthTableColumn() {
        ArrayList<Binary> binaryColumn = new ArrayList<>();
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
    public ArrayList<Binary> getBinaryColumn() {
        return this.binaryColumn;
    }
    public void setBinaryColumn(ArrayList<Binary> binaryColumn) {
        this.binaryColumn = binaryColumn;
    }
}
