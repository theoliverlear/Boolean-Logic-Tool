import java.util.ArrayList;
import java.util.Collections;

public class VariableCluster {
    boolean isPrime = false;
    ArrayList<Variable> variableCluster = new ArrayList<>();
    public VariableCluster(Variable variable) {
        this.variableCluster.add(variable);
    }
    public VariableCluster(Variable variable, boolean isPrime) {
        this.variableCluster.add(variable);
        this.isPrime = isPrime;
    }
    public VariableCluster(ArrayList<Variable> variableCluster) {
        this.variableCluster = variableCluster;
    }
    public VariableCluster(ArrayList<Variable> variableCluster, boolean isPrime) {
        this.variableCluster = variableCluster;
        this.isPrime = isPrime;
    }
    public VariableCluster(Variable... variables) {
        Collections.addAll(this.variableCluster, variables);
    }
    public ArrayList<Variable> getVariableCluster() {
        return this.variableCluster;
    }
    public void addVariable(Variable variable) {
        this.variableCluster.add(variable);
    }
    public void addVariableCluster(ArrayList<Variable> variableCluster) {
        this.variableCluster.addAll(variableCluster);
    }
    public Binary variableClusterAnd() {
        Binary result = Binary.ONE;
        for (Variable variable : this.variableCluster) {
            if (variable.getBinaryFromInput() == Binary.ZERO) {
                result = Binary.ZERO;
            }
        }
        return result;
    }
    public Binary variableClusterOr() {
        Binary result = Binary.ZERO;
        for (Variable variable : this.variableCluster) {
            if (variable.getBinaryFromInput() == Binary.ONE) {
                result = Binary.ONE;
            }
        }
        return result;
    }
    public int size() {
        return this.variableCluster.size();
    }
    public ArrayList<Character> getVariableSymbols() {
        ArrayList<Character> variableSymbols = new ArrayList<>();
        for (Variable variable : this.variableCluster) {
            variableSymbols.add(variable.getVariableSymbol());
        }
        return variableSymbols;
    }
    @Override
    public String toString() {
        String variableClusterString = "";
        if (this.isPrime) {
            variableClusterString += "(";
            for (Variable variable : this.variableCluster) {
                variableClusterString += variable.toString();
            }
            variableClusterString += ")'";
        } else {
            for (Variable variable : this.variableCluster) {
                variableClusterString += variable.toString();
            }
        }
        return variableClusterString;
    }
}
