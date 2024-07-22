import {TruthTable} from "./models/TruthTable";

const functionInput: JQuery<HTMLElement> = $("#function-input");
const submitButton: JQuery<HTMLElement> = $("#submit-button");
const truthTableSection: JQuery<HTMLElement> = $("#truth-table-section");
const formulaTitleText: JQuery<HTMLElement> = $("#formula-title-text");

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
function updateFormulaTitle(): void {
    formulaTitleText.text(functionInput.val() as string);
}

function formulaSequence(event: JQuery.KeyDownEvent): void {
    let invalidKeyDown: boolean;
    let keyValue: string = String.fromCharCode(event.which);
    invalidKeyDown = /[\d.]/.test(keyValue) || keyValue === ' ';
    if (invalidKeyDown) {
        event.preventDefault();
    }
    if (functionInput.val() === '') {
        formulaTitleText.text('Enter a formula');
    } else {
        updateFormulaTitle();
    }
}
function sendFormulaToServer(): void {
    console.log('functionInput: ', functionInput.val() as string);
    fetch('/formula', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            formula: functionInput.val() as string
        })
    }).then((response: Response): void => {
        console.log('response: ', response);
        loadTruthTableToPage(response);
    }).then((responseJson: void): void => {
        console.log(responseJson);
    }).catch(error => {
        console.error('Error: ', error);
    });
}
function loadTruthTableToPage(truthTableResponse: Response): void {
    console.log('truthTableResponse: ', truthTableResponse);
    truthTableResponse.json().then((simpleTruthTable: SimpleTruthTable): void => {
        let truthTable: TruthTable = new TruthTable(simpleTruthTable);
        truthTableSection.empty();
        truthTableSection.append(truthTable.getHtml());
    });
}
submitButton.on('click', sendFormulaToServer);
functionInput.on('keydown', formulaSequence);