import java.util.ArrayList;
import java.util.Arrays;

public class FormulaParser {
    String formula;
    int numVariables;
    ArrayList<FormulaParser> subFormulas = new ArrayList<>();
    ArrayList<VariableCluster> variableClusters = new ArrayList<>();
    TruthTable truthTable;
    public FormulaParser(String formula) {
        this.formula = formula.toUpperCase().replaceAll(" ", "");
        this.numVariables = this.formulaVariableCount();
        this.truthTable = new TruthTable(this.numVariables);
    }
    public int formulaVariableCount() {
        ArrayList<String> variableSymbols = new ArrayList<>();
        String[] formulaOrArray = this.formula.split("\\+");
        for (String variableSymbol : formulaOrArray) {
            variableSymbol = variableSymbol.replace("(", "").replace(")", "").replace("'", "");
            for (int i = 0; i < variableSymbol.length(); i++) {
                if (!variableSymbols.contains(String.valueOf(variableSymbol.charAt(i)))) {
                    variableSymbols.add(String.valueOf(variableSymbol.charAt(i)));
                }
            }
        }
        return variableSymbols.size();
    }
    public BinaryFunction getBinaryFunction() {
        String[] formulaOrArray = this.formula.split("\\+");
        if (formulaOrArray.length > 1) {
            System.out.println("OR");
            return BinaryFunction.OR;
        }
        String[] formulaNotArray = this.formula.split("'");
        if (formulaNotArray.length == 1 && formulaNotArray[0].length() == 1) {
            System.out.println("NOT");
            return BinaryFunction.NOT;
        }
        System.out.println("AND");
        return BinaryFunction.AND;
        // TODO: If after a split, the size is greater than one for the length
        //       of the string, then we recursively call to get another binary
        //       function. We continuously apply formulas until we have a set
        //       of binary digits to put into a cluster or compare with Binary
        //       object methods.
    }
    public Binary getFormulaBinary(Binary binaryDigit, Binary comparedBinaryDigit) {
        BinaryFunction binaryFunction = this.getBinaryFunction();
        Binary formulaOutput = switch (binaryFunction.getFunction()) {
            case "AND" -> {
                System.out.println("Executing AND");
                yield binaryDigit.and(comparedBinaryDigit);
            }
            case "OR" -> {
                System.out.println("Executing OR");
                yield binaryDigit.or(comparedBinaryDigit);
            }
            case "NOT" -> {
                System.out.println("Executing NOT");
                yield binaryDigit.not();
            }
            default -> throw new IllegalStateException("Unexpected value: " + binaryFunction.getFunction());
        };
        return formulaOutput;
    }
    public ArrayList<Variable> parseVariables(String formulaSubstring) {
        ArrayList<Variable> variables = new ArrayList<>();
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
    public ArrayList<Variable> parsePrimeClusters(ArrayList<Variable> variables) {
        return null;
    }
    public void findCluster() {
        // Split items in the list are OR'd together
        String[] formulaOrArray = this.formula.split("\\+");
        ArrayList<String> variableSymbols = new ArrayList<>(Arrays.asList(formulaOrArray));
        ArrayList<String> innerFormulaStrings = new ArrayList<>();
        for (int i = 0; i < variableSymbols.size(); i++) {
            String variableSymbol = variableSymbols.get(i);
            if (variableSymbol.contains("(") && !variableSymbol.contains(")")) {
                for (int j = i + 1; j < variableSymbols.size(); j++) {
                    String innerVariableSymbol = variableSymbols.get(j);
                    if (innerVariableSymbol.contains(")")) {
                        innerFormulaStrings.add(variableSymbol.replace("(", "")
                                + "+" + innerVariableSymbol.replace(")", ""));
                        variableSymbols.remove(j);
                        variableSymbols.remove(i);
                        break;
                    }
                }
            }
        }
        for (String innerFormulaString : innerFormulaStrings) {
            this.subFormulas.add(new FormulaParser(innerFormulaString));
        }
        this.subFormulas.forEach(FormulaParser::findCluster);


        ArrayList<ArrayList<Variable>> splitVariableClusters = new ArrayList<>();
        for (String variableStringCluster : variableSymbols) {
            splitVariableClusters.add(this.parseVariables(variableStringCluster));
        }
        System.out.println(splitVariableClusters);

        ArrayList<VariableCluster> variableClusters = new ArrayList<>();
        for (ArrayList<Variable> variableCluster : splitVariableClusters) {
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
                ArrayList<Variable> primeCluster = new ArrayList<>();
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
    public ArrayList<Binary> getFullFormulaBinary() {
        ArrayList<BinaryRow> binaryRows = this.truthTable.getTruthTableRows();
        ArrayList<BinaryColumn> binaryColumns = this.truthTable.getTruthTableColumns();
        ArrayList<Binary> finalBinaryOrDigits = new ArrayList<>();
        ArrayList<ArrayList<VariableCluster>> allVariableClusterList = new ArrayList<>();
        allVariableClusterList.add(this.variableClusters);
        for (FormulaParser subFormula : this.subFormulas) {
            allVariableClusterList.add(subFormula.variableClusters);
        }
        for (BinaryRow binaryRow : binaryRows) {
            ArrayList<Binary> variableClusterBinaryDigits = new ArrayList<>();
            for (ArrayList<VariableCluster> variableClusterList : allVariableClusterList) {

                for (VariableCluster variableCluster : variableClusterList) {
                    ArrayList<Character> variableSymbols = variableCluster.getVariableSymbols();
                    char[] binaryDigitsOrder = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
                    ArrayList<Binary> binaryDigits = binaryRow.getBinaryRow();
                    char[] binaryDigitsOrderSubset = Arrays.copyOf(binaryDigitsOrder, this.numVariables);
                    char[] finalBinaryVariableOrder = new char[this.numVariables];
//                    for (int i = binaryDigitsOrderSubset.length - 1, j = 0; i >= 0 && j < binaryDigitsOrderSubset.length; i--, j++) {
//                        finalBinaryVariableOrder[j] = binaryDigitsOrderSubset[i];
//                    }
                    for (int i = 0; i < binaryDigitsOrderSubset.length; i++) {
                        finalBinaryVariableOrder[i] = binaryDigitsOrderSubset[i];
                    }
                    ArrayList<Binary> binaryClusterDigits = new ArrayList<>();
                    for (int i = 0; i < variableSymbols.size(); i++) {
                        char variableSymbol = variableSymbols.get(i);
                        for (Variable variable : variableCluster.getVariableCluster()) {
                            if (variable.getVariableSymbol() == variableSymbol) {
                                int variableIndex;
                                for (variableIndex = 0; variableIndex < finalBinaryVariableOrder.length; variableIndex++) {
                                    if (finalBinaryVariableOrder[variableIndex] == variableSymbol) {
                                        break;
                                    }
                                }
                                if (variableIndex == finalBinaryVariableOrder.length) {
                                    continue;
                                }
                                Binary binaryDigit = binaryDigits.get(variableIndex);
                                binaryClusterDigits.add(variable.getBinaryFromInput(binaryDigit));
                            }
                        }
                    }
                    BinaryRow binaryClusterRow = new BinaryRow(binaryClusterDigits);
                    Binary binaryClusterOutput = binaryClusterRow.rowAnd();
                    if (variableCluster.getIsPrime()) {
                        binaryClusterOutput = binaryClusterOutput.not();
                    }
                    variableClusterBinaryDigits.add(binaryClusterOutput);
                }
            }
            BinaryRow variableClusterBinaryRow = new BinaryRow(variableClusterBinaryDigits);
            Binary variableClusterBinaryOutput = variableClusterBinaryRow.rowOr();
            finalBinaryOrDigits.add(variableClusterBinaryOutput);
        }
        BinaryColumn finalBinaryColumn = new BinaryColumn(finalBinaryOrDigits);
        TruthTable finalTruthTable = new TruthTable(this.numVariables, finalBinaryColumn);
        System.out.println(finalTruthTable);
        return finalBinaryOrDigits;
    }
    @Override
    public String toString() {
        return this.formula;
    }
    public static void main(String[] args) {
//        FormulaParser formulaParser = new FormulaParser("A'");
//        System.out.println(formulaParser.parsePrimeVariable());
//        Binary binaryDigit = Binary.ONE;
//        Binary comparedBinaryDigit = Binary.ZERO;
//        System.out.println(formulaParser.getFormulaBinary(binaryDigit, comparedBinaryDigit));
//        FormulaParser formulaParser = new FormulaParser("(A'C+B)'+(CB')'+A'B'C'");
        FormulaParser formulaParser = new FormulaParser("AB+C");
        formulaParser.findCluster();
        System.out.println(formulaParser);
        System.out.println(formulaParser.getFullFormulaBinary());

    }
}
