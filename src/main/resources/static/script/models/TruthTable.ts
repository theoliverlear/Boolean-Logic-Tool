import {SimpleTruthTable} from "../homeScript";

export class TruthTable {
    private _simpleTruthTable: SimpleTruthTable;
    private _numVariables: number;
    private _variables: string[];
    private _numColumns: number;
    private _numRows: number;
    private _truthTableBinaryArray: string[][];
    public static readonly VARIABLES: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
    constructor(simpleTruthTable: SimpleTruthTable) {
        this._simpleTruthTable = simpleTruthTable;
        this.parseSimpleTruthTable();
    }
    parseSimpleTruthTable(): void {
        this._numVariables = this._simpleTruthTable.truthTable.numVariables;
        this._variables = TruthTable.VARIABLES.slice(0, this._numVariables);
        this._numColumns = this._simpleTruthTable.truthTable.truthTableColumns.length;
        this._numRows = this._simpleTruthTable.truthTable.truthTableRows.length;
        this._truthTableBinaryArray = new Array(this._numColumns);
        for (let i: number = 0; i < this._numColumns; i++) {
            this._truthTableBinaryArray[i] = new Array(this._numRows);
        }
        for (let i: number = 0; i < this._numColumns; i++) {
            for (let j: number = 0; j < this._numRows; j++) {
                this._truthTableBinaryArray[i][j] = this._simpleTruthTable.truthTable.truthTableColumns[i].binaryColumn[j].binary;
            }
        }
    }
    getVariablesHtmlString(): string {
        let variablesHtmlString: string = '';
        for (let i: number = 0; i < this._numVariables; i++) {
            variablesHtmlString += `
                <div class="variable-div">
                    <h4>${this._variables[i]}</h4>
                </div>
                `;
        }
        variablesHtmlString += `
            <div class="variable-div function-cell">
                <h4>f</h4>
            </div>
            `;
        return variablesHtmlString;
    }
    getVariablesHtml(): HTMLDivElement {
        let variablesHtml: HTMLDivElement = document.createElement('div');
        variablesHtml.classList.add('variables-div');
        variablesHtml.innerHTML = this.getVariablesHtmlString();
        return variablesHtml;
    }

    getTruthTableRowHtmlString(rowIndex: number): string {
        let truthTableRowHtmlString: string = '';
        for (let i: number = 0; i < this._numColumns; i++) {
            truthTableRowHtmlString += `
                <div class="truth-table-cell">
                    <h4>${this._truthTableBinaryArray[i][rowIndex]}</h4>
                </div>
                `;
        }
        truthTableRowHtmlString += `
            <div class="truth-table-cell function-cell">
                <h4>${this._simpleTruthTable.truthTable.functionColumn.binaryColumn[rowIndex].binary}</h4>
            </div>
            `;
        return truthTableRowHtmlString;
    }
    getTruthTableRowHtml(rowIndex: number): HTMLDivElement {
        let truthTableRowHtml: HTMLDivElement = document.createElement('div');
        truthTableRowHtml.classList.add('truth-table-row');
        truthTableRowHtml.innerHTML = this.getTruthTableRowHtmlString(rowIndex);
        return truthTableRowHtml;
    }
    getHtml(): HTMLDivElement {
        let truthTableHtml: HTMLDivElement = document.createElement('div');
        truthTableHtml.classList.add('truth-table-div');
        truthTableHtml.appendChild(this.getVariablesHtml());
        for (let i: number = 0; i < this._numRows; i++) {
            truthTableHtml.appendChild(this.getTruthTableRowHtml(i));
        }
        return truthTableHtml;
    }
    get simpleTruthTable(): SimpleTruthTable {
        return this._simpleTruthTable;
    }
    set simpleTruthTable(value: SimpleTruthTable) {
        this._simpleTruthTable = value;
    }
}