import java.util.ArrayList;

public class BinaryRow {
    ArrayList<Binary> binaryRow;
    public BinaryRow() {
        this.binaryRow = new ArrayList<>();
    }
    public BinaryRow(ArrayList<Binary> binaryRow) {
        this.binaryRow = binaryRow;
    }
    public BinaryRow(Binary... binaries) {
        this.binaryRow = new ArrayList<>();
        for (Binary binary : binaries) {
            this.binaryRow.add(binary);
        }
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
}
