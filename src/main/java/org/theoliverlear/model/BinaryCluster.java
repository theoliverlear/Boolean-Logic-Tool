package org.theoliverlear.model;
//=================================-Imports-==================================
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryCluster {
    List<Binary> binaryCluster;
    public BinaryCluster() {
        this.binaryCluster = new ArrayList<>();
    }
    public BinaryCluster(List<Binary> binaryCluster) {
        this.binaryCluster = binaryCluster;
    }
    public BinaryCluster(Binary... binaries) {
        this.binaryCluster = new ArrayList<>();
        Collections.addAll(this.binaryCluster, binaries);
    }
    public Binary and(BinaryCluster comparedBinaryCluster) {
        Binary thisClusterAnd = this.clusterAnd();
        Binary comparedClusterAnd = comparedBinaryCluster.clusterAnd();
        return thisClusterAnd.and(comparedClusterAnd);
    }
    public Binary or(BinaryCluster comparedBinaryCluster) {
        Binary thisClusterOr = this.clusterOr();
        Binary comparedClusterOr = comparedBinaryCluster.clusterOr();
        return thisClusterOr.or(comparedClusterOr);
    }
    public Binary clusterAnd() {
        for (Binary binaryDigit : this.binaryCluster) {
            if (binaryDigit.equals(Binary.ZERO)) {
                return Binary.ZERO;
            }
        }
        return Binary.ONE;
    }
    public Binary clusterOr() {
        for (Binary binaryDigit : this.binaryCluster) {
            if (binaryDigit.equals(Binary.ONE)) {
                return Binary.ONE;
            }
        }
        return Binary.ZERO;
    }
}
