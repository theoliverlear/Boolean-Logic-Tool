public class FormulaParser {
    String formula;
    public FormulaParser(String formula) {
        this.formula = formula;
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

    public static void main(String[] args) {
        FormulaParser formulaParser = new FormulaParser("A'");
        Binary binaryDigit = Binary.ONE;
        Binary comparedBinaryDigit = Binary.ZERO;
        System.out.println(formulaParser.getFormulaBinary(binaryDigit, comparedBinaryDigit));
    }
}
