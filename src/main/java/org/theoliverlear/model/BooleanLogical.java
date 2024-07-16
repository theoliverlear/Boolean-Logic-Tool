package org.theoliverlear.model;

public interface BooleanLogical<T> {
    Binary not();
    Binary and(T comparedBinary);
    Binary nand(T comparedBinary);
    Binary or(T comparedBinary);
    Binary nor(T comparedBinary);
    Binary xor(T comparedBinary);
}
