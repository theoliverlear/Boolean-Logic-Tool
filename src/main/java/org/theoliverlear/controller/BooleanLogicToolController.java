package org.theoliverlear.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.comm.request.FormulaRequest;
import org.theoliverlear.model.BinaryFormula;
import org.theoliverlear.model.TruthTable;

@Controller
public class BooleanLogicToolController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }
    @RequestMapping("/formula")
    public ResponseEntity<TruthTable> formula(@RequestBody FormulaRequest formulaRequest) {
        BinaryFormula formula = new BinaryFormula(formulaRequest.getFormula());
        return new ResponseEntity<>(formula.getTruthTable(), HttpStatus.OK);
    }
}
