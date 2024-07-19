package org.theoliverlear.comm.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormulaRequest {
    private String formula;
    public FormulaRequest(String formula) {
        this.formula = formula;
    }
}
