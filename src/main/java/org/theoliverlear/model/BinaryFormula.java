package org.theoliverlear.model;
//=================================-Imports-==================================
import java.util.*;

public class BinaryFormula {
    //============================-Variables-=================================
    String formula;
    int numVariables;
    boolean isPrime = false;
    List<BinaryFormula> subFormulas;
    List<VariableCluster> variableClusters;
    TruthTable truthTable;
    //===========================-Constructors-===============================
    public BinaryFormula(String formula) {
        this.subFormulas = new ArrayList<>();
        this.variableClusters = new ArrayList<>();
        this.formula = formatFormula(formula);
        this.numVariables = this.formulaVariableCount();
        this.truthTable = new TruthTable(this.numVariables);
    }
    public BinaryFormula(String formula, boolean isPrime) {
        this.variableClusters = new ArrayList<>();
        this.subFormulas = new ArrayList<>();
        this.formula = formatFormula(formula);
        this.isPrime = isPrime;
        this.numVariables = this.formulaVariableCount();
        this.truthTable = new TruthTable(this.numVariables);
    }
    //=============================-Methods-==================================
    public static String formatFormula(String formula) {
        return formula.toUpperCase().replaceAll(" ", "");
    }
    public int formulaVariableCount() {
        List<String> variableSymbols = new ArrayList<>();
        char[] symbols = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        String[] formulaOrArray = this.formula.split("\\+");
        for (String variableSymbol : formulaOrArray) {
            variableSymbol = variableSymbol.replace("(", "").replace(")", "").replace("'", "");
            for (int i = 0; i < variableSymbol.length(); i++) {
                if (!variableSymbols.contains(String.valueOf(variableSymbol.charAt(i)))) {
                    variableSymbols.add(String.valueOf(variableSymbol.charAt(i)));
                }
            }
        }
        variableSymbols.sort(String::compareToIgnoreCase);
        List<String> variablesToAdd = new ArrayList<>();
        for (int i = 0; i < variableSymbols.size(); i++) {
            if (!variableSymbols.get(i).equals(String.valueOf(symbols[i]))) {
                variablesToAdd.add(String.valueOf(symbols[i]));
            }
        }
        variableSymbols.addAll(variablesToAdd);
        Set<String> variableSymbolsSet = new HashSet<>(variableSymbols);
        return variableSymbolsSet.size();
    }
    public List<Variable> parseVariables(String formulaSubstring) {
        List<Variable> variables = new ArrayList<>();
        int formulaLength = formulaSubstring.length();
        String[] primeVariableSplitArray = formulaSubstring.split("'");
        System.out.println(Arrays.toString(primeVariableSplitArray));
        for (int i = 0; i < formulaLength; i++) {
            if (i + 1 < formulaLength) {
                if (formulaSubstring.charAt(i + 1) == '\'') {
                    variables.add(new Variable(formulaSubstring.charAt(i), true));
                    continue;
                }
            }
            if (formulaSubstring.charAt(i) != '\'') {
                variables.add(new Variable(formulaSubstring.charAt(i)));
            }
        }
        return variables;
    }
    public void findVariableClusters() {
        // Split items in the list are OR'd together
        String[] formulaOrArray = this.formula.split("\\+");
        List<String> variableSymbols = new ArrayList<>(Arrays.asList(formulaOrArray));
        List<String> innerFormulaStrings = new ArrayList<>();
        List<Map<String, Boolean>> innerFormulaWithPrimeMap = new ArrayList<>();
        for (int i = 0; i < variableSymbols.size(); i++) {
            String variableSymbol = variableSymbols.get(i);
            if (variableSymbol.contains("(") && !variableSymbol.contains(")")) {
                for (int j = i + 1; j < variableSymbols.size(); j++) {
                    String innerVariableSymbol = variableSymbols.get(j);
                    if (innerVariableSymbol.contains(")'")) {
                        String withoutPrimeBrackets = variableSymbol.replace("(", "")
                                + "+" + innerVariableSymbol.replace(")'", "");
                        Map<String, Boolean> innerFormulaMap = new HashMap<>();
                        innerFormulaMap.put(withoutPrimeBrackets, true);
                        innerFormulaWithPrimeMap.add(innerFormulaMap);
                        variableSymbols.remove(j);
                        variableSymbols.remove(i);
                        break;
                    } else if (innerVariableSymbol.contains(")")) {
                        String withoutPrimeBrackets = variableSymbol.replace("(", "")
                                + "+" + innerVariableSymbol.replace(")", "");
                        Map<String, Boolean> innerFormulaMap = new HashMap<>();
                        innerFormulaMap.put(withoutPrimeBrackets, false);
                        innerFormulaWithPrimeMap.add(innerFormulaMap);
                        variableSymbols.remove(j);
                        variableSymbols.remove(i);
                        break;
                    }
                }
            }
        }
        for (Map<String, Boolean> innerFormulaMap : innerFormulaWithPrimeMap) {
            for (Map.Entry<String, Boolean> entry : innerFormulaMap.entrySet()) {
                String innerFormulaString = entry.getKey();
                boolean isPrime = entry.getValue();
                this.subFormulas.add(new BinaryFormula(innerFormulaString, isPrime));
            }
        }
        this.subFormulas.forEach(BinaryFormula::findVariableClusters);
        List<List<Variable>> splitVariableClusters = new ArrayList<>();
        for (String variableStringCluster : variableSymbols) {
            splitVariableClusters.add(this.parseVariables(variableStringCluster));
        }
        System.out.println(splitVariableClusters);
        List<VariableCluster> variableClusters = new ArrayList<>();
        for (List<Variable> variableCluster : splitVariableClusters) {
            boolean isPrime = false;
            int clusterPrimeOpenBracketIndex = -1;
            int clusterPrimeCloseBracketIndex = -1;
            for (int i = 0; i < variableCluster.size(); i++) {
                Variable variable = variableCluster.get(i);
                if (variable.getVariableSymbol() == '(') {
                    isPrime = true;
                    clusterPrimeOpenBracketIndex = i;
                    break;
                }
            }
            if (isPrime) {
                for (int i = clusterPrimeOpenBracketIndex; i < variableCluster.size(); i++) {
                    Variable variable = variableCluster.get(i);
                    if (variable.getVariableSymbol() == ')') {
                        clusterPrimeCloseBracketIndex = i;
                        break;
                    }
                }
            }
            if (isPrime) {
                List<Variable> primeCluster = new ArrayList<>();
                for (int i = clusterPrimeOpenBracketIndex + 1; i < clusterPrimeCloseBracketIndex; i++) {
                    primeCluster.add(variableCluster.get(i));
                }
                VariableCluster parsedPrimeCluster = new VariableCluster(primeCluster, true);
                System.out.println(parsedPrimeCluster);
                variableClusters.add(parsedPrimeCluster);
            } else {
                variableClusters.add(new VariableCluster(variableCluster));
            }
            System.out.println(variableClusters);
        }
        this.variableClusters = variableClusters;
    }
    public List<Binary> getFullFormulaBinary() {
        List<BinaryRow> binaryRows = this.truthTable.getTruthTableRows();
        List<Binary> finalBinaryDigits = new ArrayList<>();
        List<BinaryFormula> formulas = new ArrayList<>();
        formulas.add(this);
        formulas.addAll(this.subFormulas);

        /*
        - Final output
        - Formula - OR
        - Variable cluster - AND
        - Variable - AND
         */

        // Loop through each row. This will be the final formula combination
        // output.
        for (BinaryRow binaryRow : binaryRows) { // Look for final output of each row
            List<Binary> formulasOutputDigits = new ArrayList<>();
            // Loop through each formula. This will be the OR'd with the
            // output of other formulas.
            for (BinaryFormula formula : formulas) { // Look for final output of each formula
                List<VariableCluster> clustersInFormula = formula.variableClusters;
                List<Binary> formulaClusters = new ArrayList<>();
                // For each cluster in the formula, we will get the output
                // of the cluster and then AND its contents.
                for (VariableCluster variableCluster : clustersInFormula) { // Look for final output of each cluster
                    List<Character> variableSymbols = variableCluster.getVariableSymbols();
                    char[] binaryDigitsOrder = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
                    List<Binary> binaryDigits = binaryRow.getBinaryRow();
                    char[] binaryDigitsOrderSubset = Arrays.copyOf(binaryDigitsOrder, this.numVariables);
                    List<Binary> binaryClusterDigits = new ArrayList<>();
                    for (char variableSymbol : variableSymbols) {
                        // Loop through each variable in the cluster and get
                        // the binary digit from the input. We will then AND
                        // the binary digits together.
                        List<Variable> variableInsideCluster = variableCluster.getVariableCluster();
                        for (Variable variable : variableInsideCluster) { // Look for final output of each variable
                            if (variable.getVariableSymbol() == variableSymbol) {
                                int variableIndex;
                                for (variableIndex = 0; variableIndex < binaryDigitsOrderSubset.length; variableIndex++) {
                                    if (binaryDigitsOrderSubset[variableIndex] == variableSymbol) {
                                        break;
                                    }
                                }
                                if (variableIndex == binaryDigitsOrderSubset.length) {
                                    continue;
                                }
                                Binary binaryDigit = binaryDigits.get(variableIndex);
                                binaryClusterDigits.add(variable.getBinaryFromInput(binaryDigit));
                            }
                        } // All variables in the cluster found
                    }
                    BinaryRow binaryClusterRow = new BinaryRow(binaryClusterDigits);
                    Binary binaryClusterOutput = binaryClusterRow.rowAnd();
                    if (variableCluster.getIsPrime()) {
                        binaryClusterOutput = binaryClusterOutput.not();
                    }
                    formulaClusters.add(binaryClusterOutput);
                } // All clusters binary digits found
                BinaryRow variableClustersBinaryRow = new BinaryRow(formulaClusters);
                Binary variableClustersBinaryOutput = variableClustersBinaryRow.rowOr();
                if (formula.isPrime) {
                    variableClustersBinaryOutput = variableClustersBinaryOutput.not();
                }
                formulasOutputDigits.add(variableClustersBinaryOutput);
            } // All formula binary digits found
            BinaryRow formulaRow = new BinaryRow(formulasOutputDigits);
            Binary formulaRowOutput = formulaRow.rowOr();
            finalBinaryDigits.add(formulaRowOutput);
        } // All function binary digits found
        BinaryColumn finalBinaryColumn = new BinaryColumn(finalBinaryDigits);
        TruthTable finalTruthTable = new TruthTable(this.numVariables, finalBinaryColumn);
        System.out.println(finalTruthTable);
        return finalBinaryDigits;
    }
    public void simplifyFormula() {

    }
    @Override
    public String toString() {
        return this.formula;
    }
    public static void main(String[] args) {
        BinaryFormula binaryFormula = new BinaryFormula("D'+B'C+AC");
        binaryFormula.findVariableClusters();
        System.out.println(binaryFormula);
        System.out.println(binaryFormula.getFullFormulaBinary());
        System.out.println("-".repeat(10));
        BinaryFormula binaryFormula2 = new BinaryFormula("D'+AC");
        binaryFormula2.findVariableClusters();
        System.out.println(binaryFormula2);
        System.out.println(binaryFormula2.getFullFormulaBinary());
        System.out.println(binaryFormula.getFullFormulaBinary().equals(binaryFormula2.getFullFormulaBinary()));

    }
}
