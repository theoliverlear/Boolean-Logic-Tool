import {TruthTable} from "./models/TruthTable";

const functionInput: JQuery<HTMLElement> = $("#function-input");
const submitButton: JQuery<HTMLElement> = $("#submit-button");
const truthTableDiv: JQuery<HTMLElement> = $("#truth-table-div");
const truthTableSection: JQuery<HTMLElement> = $("#truth-table-section");

type SimpleBinary = {
    binary: string
}

type SimpleTableColumn = {
    numVariables: number,
    positionFromLast: number,
    binaryColumn: SimpleBinary[]
}

type SimpleTableRow = {
    binaryRow: SimpleBinary[]
}

export type SimpleTruthTable = {
    truthTable: {
        numVariables: number,
        truthTableColumns: SimpleTableColumn[],
        truthTableRows: SimpleTableRow[],
        functionColumn: SimpleTableColumn
    }
}

function sendFormulaToServer() {
    console.log('functionInput: ', functionInput.val() as string);
    fetch('/formula', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            formula: functionInput.val() as string
        })
    }).then((response: Response) => {
        console.log('response: ', response);
        loadTruthTableToPage(response);
    }).then(responseJson => {
        console.log(responseJson);
    }).catch(error => {
        console.error('Error: ', error);
    });
}
function loadTruthTableToPage(truthTableResponse: Response): void {
    console.log('truthTableResponse: ', truthTableResponse);
    truthTableResponse.json().then((simpleTruthTable: SimpleTruthTable) => {
        let truthTable: TruthTable = new TruthTable(simpleTruthTable);
        truthTableSection.empty();
        truthTableSection.append(truthTable.getHtml());
    });
}
submitButton.on('click', sendFormulaToServer);