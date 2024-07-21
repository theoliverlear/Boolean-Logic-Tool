package org.theoliverlear.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.comm.request.FormulaRequest;
import org.theoliverlear.comm.response.TruthTableResponse;
import org.theoliverlear.model.BinaryFormula;
import org.theoliverlear.model.TruthTable;

@Controller
public class BooleanLogicToolController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }
    @RequestMapping("/formula")
    public ResponseEntity<TruthTableResponse> formula(@RequestBody FormulaRequest formulaRequest) {
        System.out.println("Formula Request Formula: " + formulaRequest.getFormula());
        BinaryFormula formula = new BinaryFormula(formulaRequest.getFormula());
        System.out.println("Formula: " + formula);
        System.out.println("Truth Table: \n" + formula.getTruthTable());
        return new ResponseEntity<>(new TruthTableResponse(formula.getTruthTable()), HttpStatus.OK);
    }
}
