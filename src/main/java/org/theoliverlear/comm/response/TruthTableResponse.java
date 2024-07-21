package org.theoliverlear.comm.response;

import lombok.Data;
import org.theoliverlear.model.TruthTable;

@Data
public class TruthTableResponse {
    private TruthTable truthTable;
    public TruthTableResponse(TruthTable truthTable) {
        this.truthTable = truthTable;
    }
}
