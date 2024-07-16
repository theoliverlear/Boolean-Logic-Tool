package org.theoliverlear.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
            if (this.binaryValue.equals(Binary.ONE)) {
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
            if (inputDigit.equals(Binary.ONE)) {
                return Binary.ZERO;
            } else {
                return Binary.ONE;
            }
        } else {
            return inputDigit;
        }
    }
    @Override
    public String toString() {
        if (this.isPrime) {
            return this.variableSymbol + "'";
        }
        return String.valueOf(this.variableSymbol);
    }

}
