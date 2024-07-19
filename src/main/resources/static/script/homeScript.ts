const functionInput: JQuery<HTMLElement> = $("#function-input");
const submitButton: JQuery<HTMLElement> = $("#submit-button");
const truthTableDiv: JQuery<HTMLElement> = $("#truth-table-div");

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
    }).then((response: Response) => response.json()).then(responseJson => {
        console.log(responseJson);
        let truthTable: string = responseJson.truthTable;
        truthTableDiv.text(truthTable);
    }).catch(error => {
        console.error('Error: ', error);
    });
}
function loadTruthTableToPage() {

}
submitButton.on('click', sendFormulaToServer);