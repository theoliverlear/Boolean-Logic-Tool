public class Variable {
    char variableSymbol;
    Binary binaryValue = Binary.ZERO;
    boolean isPrime;
    public Variable(char variableSymbol) {
        this.variableSymbol = variableSymbol;
    }
    public Variable(char variableSymbol, Binary binaryValue) {
        this.variableSymbol = variableSymbol;
        this.binaryValue = binaryValue;
    }
    public Variable(char variableSymbol, boolean isPrime) {
        System.out.println("Variable: " + variableSymbol + " is prime: " + isPrime);
        this.variableSymbol = variableSymbol;
        this.isPrime = isPrime;
    }
    public Variable(char variableSymbol, Binary binaryValue, boolean isPrime) {
        this.variableSymbol = variableSymbol;
        this.binaryValue = binaryValue;
        this.isPrime = isPrime;
    }
    public Binary getBinaryFromInput() {
        if (this.isPrime) {
            if (this.binaryValue == Binary.ONE) {
                return Binary.ZERO;
            } else {
                return Binary.ONE;
            }
        } else {
            return this.binaryValue;
        }
    }
    public Binary getBinaryFromInput(Binary inputDigit) {
        if (this.isPrime) {
            if (inputDigit == Binary.ONE) {
                return Binary.ZERO;
            } else {
                return Binary.ONE;
            }
        } else {
            return this.binaryValue;
        }
    }
    public char getVariableSymbol() {
        return this.variableSymbol;
    }
    public Binary getBinaryValue() {
        return this.binaryValue;
    }
    public void setVariableSymbol(char variableSymbol) {
        this.variableSymbol = variableSymbol;
    }
    public void setBinaryValue(Binary binaryValue) {
        this.binaryValue = binaryValue;
    }
    @Override
    public String toString() {
        if (this.isPrime) {
            return this.variableSymbol + "'";
        }
        return String.valueOf(this.variableSymbol);
    }

}
