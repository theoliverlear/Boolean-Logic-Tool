package org.theoliverlear.model;

import java.util.ArrayList;
@Deprecated
public class Main {
    public static ArrayList<String> getTruthTableColumn(int positionFromLast, int numVariables) {
        ArrayList<String> binaryColumn = new ArrayList<>();
        int numCombinations = (int) Math.pow(2, numVariables);
        int numChangesAmount = (int) Math.pow(2, positionFromLast);
        int rowPrintCount = 0;
        String zero = "0";
        String one = "1";
        String currentNum = zero;
        int currentNumCount = 0;
        while (rowPrintCount < numCombinations) {
            if (currentNumCount == numChangesAmount) {
                if (currentNum.equals(zero)) {
                    currentNum = one;
                } else {
                    currentNum = zero;
                }
                currentNumCount = 0;
            }
            binaryColumn.add(currentNum);
            currentNumCount++;
            rowPrintCount++;
        }
        return binaryColumn;
    }
    public static String and(String baseBinary, String comparedBinary) {
        boolean isBothOne = baseBinary.equals("1") && comparedBinary.equals("1");
        if (isBothOne) {
            return "1";
        } else {
            return "0";
        }
    }
    public static String and(String... binaries) {
        for (String binaryDigit : binaries) {
            if (binaryDigit.equals("0")) {
                return "0";
            }
        }
        return "1";
    }
    public static String or(String baseBinary, String comparedBinary) {
        boolean isEitherOne = baseBinary.equals("1") || comparedBinary.equals("1");
        if (isEitherOne) {
            return "1";
        } else {
            return "0";
        }
    }
    public static String not(String baseBinary) {
        if (baseBinary.equals("1")) {
            return "0";
        } else {
            return "1";
        }
    }
    public static ArrayList<String> generateTruthTable(int numVariables) {
        ArrayList<String> combinedBinaryTable = new ArrayList<>();
        int numCombinations = (int) Math.pow(2, numVariables);
        ArrayList<ArrayList<String>> binaryColumns = new ArrayList<>();
        for (int i = numVariables - 1; i >= 0; i--) {
            binaryColumns.add(getTruthTableColumn(i, numVariables));
        }
        for (int i = 0; i < numCombinations; i++) {
            String combinedBinary = "";
            for (int j = 0; j < binaryColumns.size(); j++) {
                combinedBinary += binaryColumns.get(j).get(i);
            }
            combinedBinaryTable.add(combinedBinary);
        }
        return combinedBinaryTable;
    }
    public static void printTruthTable(ArrayList<String> combinedBinaryTable) {
        for (String binary : combinedBinaryTable) {
            System.out.println(binary);
        }
    }
    public static void printTruthTable(ArrayList<String> combinedBinaryTable, ArrayList<String> functionColumn) {
        for (int i = 0; i < combinedBinaryTable.size(); i++) {
            String truthTableBinary = combinedBinaryTable.get(i);
            System.out.print(truthTableBinary);
            System.out.println("|" + functionColumn.get(i));
        }
    }
    public static void printTruthTable(int numVariables) {
        String zero = "0";
        String one = "1";
        int varCountA = 0;
        int varCountB = 0;

        int rowPrintCount = 0;
        int numCombinations = (int) Math.pow(2, numVariables);
        while (rowPrintCount < numCombinations) {
            if (varCountA == 0 || varCountA == 1) {
                System.out.print(zero);
            } else {
                System.out.print(one);
            }
            if (varCountB % 2 == 0) {
                System.out.println(zero);
            } else {
                System.out.println(one);
            }
            if (varCountA == 3) {
                varCountA = 0;
            } else {
                varCountA++;
            }
            varCountB++;
            rowPrintCount++;
        }
    }
    public static String function(String combinedBinary) {
        ArrayList<String> separatedBinary = separateBinary(combinedBinary);
        String[] separatedBinaryArray = separatedBinary.toArray(new String[0]);
        String binaryCombinationFunction = and(separatedBinaryArray);
        return binaryCombinationFunction;
    }
    public static ArrayList<String> separateBinary(String combinedBinary) {
        ArrayList<String> separatedBinary = new ArrayList<>();
        for (int i = 0; i < combinedBinary.length(); i++) {
            separatedBinary.add(combinedBinary.substring(i, i + 1));
        }
        return separatedBinary;
    }
    public static ArrayList<String> functionColumn(ArrayList<String> combinedBinaryTable) {
        ArrayList<String> functionColumn = new ArrayList<>();
        for (String combinedBinary : combinedBinaryTable) {
            functionColumn.add(function(combinedBinary));
        }
        return functionColumn;
    }
    public static void main(String[] args) {
        System.out.println(and("0", "0"));
        System.out.println(and("0", "1"));
        System.out.println(and("1", "0"));
        System.out.println(and("1", "1"));
        System.out.println();
        System.out.println(or("0", "0"));
        System.out.println(or("0", "1"));
        System.out.println(or("1", "0"));
        System.out.println(or("1", "1"));
        System.out.println();
        System.out.println(not("0"));
        System.out.println();
        printTruthTable(2);
        System.out.println();
        System.out.println(getTruthTableColumn(2, 3));
        System.out.println();
        ArrayList<String> defaultTruthTable = generateTruthTable(3);
        printTruthTable(defaultTruthTable);
        System.out.println();
        System.out.println(functionColumn(defaultTruthTable));
        System.out.println();
        printTruthTable(defaultTruthTable, functionColumn(defaultTruthTable));
    }
}
