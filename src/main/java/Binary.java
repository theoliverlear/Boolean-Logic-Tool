//=================================-Imports-==================================
import java.util.ArrayList;

public class Binary {
    //============================-Variables-=================================
    public static final Binary ZERO = new Binary("0");
    public static final Binary ONE = new Binary("1");
    String binary;
    //===========================-Constructors-===============================
    public Binary(String binary) {
        this.binary = binary;
    }
    //=============================-Methods-==================================
    public Binary and(Binary comparedBinary) {
        System.out.printf("%s and %s%n", this, comparedBinary);
        boolean isBothOne = this.equals(ONE) && comparedBinary.equals(ONE);
        if (isBothOne) {
            return ONE;
        } else {
            return ZERO;
        }
    }
    public Binary and(Binary... comparedBinaries) {
        if (this.equals(ZERO)) {
            return ZERO;
        }
        for (Binary binaryDigit : comparedBinaries) {
            if (binaryDigit.equals(ZERO)) {
                return ZERO;
            }
        }
        return ONE;
    }
    public Binary and(ArrayList<Binary> comparedBinaries) {
        if (this.equals(ZERO)) {
            return ZERO;
        }
        for (Binary binaryDigit : comparedBinaries) {
            if (binaryDigit.equals(ZERO)) {
                return ZERO;
            }
        }
        return ONE;
    }
    public static Binary and(Binary baseBinary, Binary comparedBinary) {
        boolean isBothOne = baseBinary.equals(ONE) && comparedBinary.equals(ONE);
        if (isBothOne) {
            return ONE;
        } else {
            return ZERO;
        }
    }
    public Binary nand(Binary comparedBinary) {
        return this.and(comparedBinary).not();
    }
    public Binary nand(Binary... comparedBinaries) {
        return this.and(comparedBinaries).not();
    }
    public Binary nand(ArrayList<Binary> comparedBinaries) {
        return this.and(comparedBinaries).not();
    }
    public Binary nor(Binary comparedBinary) {
        return this.or(comparedBinary).not();
    }
    public Binary nor(Binary... comparedBinaries) {
        return this.or(comparedBinaries).not();
    }
    public Binary nor(ArrayList<Binary> comparedBinaries) {
        return this.or(comparedBinaries).not();
    }
    public Binary or(Binary comparedBinary) {
        boolean isEitherOne = this.equals(ONE) || comparedBinary.equals(ONE);
        if (isEitherOne) {
            return ONE;
        } else {
            return ZERO;
        }
    }
    public Binary or(Binary... comparedBinaries) {
        for (Binary binaryDigit : comparedBinaries) {
            if (binaryDigit.equals(ONE)) {
                return ONE;
            }
        }
        return ZERO;
    }
    public Binary or(ArrayList<Binary> comparedBinaries) {
        for (Binary binaryDigit : comparedBinaries) {
            if (binaryDigit.equals(ONE)) {
                return ONE;
            }
        }
        return ZERO;
    }
    public Binary not() {
        if (this.equals(ONE)) {
            return ZERO;
        } else {
            return ONE;
        }
    }
    @Override
    public String toString() {
        return this.binary;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Binary comparedBinary) {
            return this.binary.equals(comparedBinary.getBinary());
        }
        return false;
    }
    public String getBinary() {
        return this.binary;
    }
    public void setBinary(String binary) {
        this.binary = binary;
    }
}
