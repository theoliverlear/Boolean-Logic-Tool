public class BinaryFunction {
    //============================-Variables-=================================
    public static final BinaryFunction AND = new BinaryFunction("AND");
    public static final BinaryFunction OR = new BinaryFunction("OR");
    public static final BinaryFunction NOT = new BinaryFunction("NOT");
    String function;
    //===========================-Constructors-===============================
    public BinaryFunction() {
        this.function = "";
    }
    public BinaryFunction(String function) {
        this.function = function;
    }
    //=============================-Methods-==================================
    public BinaryFunction determineFunction(BinaryCluster binaryCluster) {
        if (binaryCluster.clusterAnd() == Binary.ONE) {
            return AND;
        } else if (binaryCluster.clusterOr() == Binary.ONE) {
            return OR;
        } else {
            return null;
        }
    }
    public String getFunction() {
        return this.function;
    }
    public String toString() {
        return this.function;
    }
}
