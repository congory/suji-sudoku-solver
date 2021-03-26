/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sujisudokusolver;

/**
 *
 * @author Yves
 */
public class Engine {

    private int deletionsUntilSolved = 243; //3x81 cause every deletion is reported three times (row, col, block
    private boolean solved = false;
    private RowColBlock[] rows;
    private RowColBlock[] cols;
    private RowColBlock[] blocks;
    private Element[][] elements = new Element[9][9];

    public Engine() {
        initialize();
    }

    public void reset() {
        initialize();
    }

    public void setInitialState(String[][] initialState) {
        
        //run deletion
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(initialState[i][j] != null){
                    this.elements[i][j].setNumber(Integer.parseInt(initialState[i][j]));
                }
            }
        }
      
        //if deletion wasn't enough run method of elemination (Ausschlussverfahren)
        for(int k = 0; k < 9; k++){
            if(this.isSolved()){
                return;
            }
            System.out.println("Deletion not enough -> running method of elemination");
            rows[k].runMethodOfElemination();
            cols[k].runMethodOfElemination();
            blocks[k].runMethodOfElemination();
        }
    }
    
    public String[][] getCurrentState() {
        String[][] currentState = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                currentState[i][j] = elements[i][j].getNumber();
            }
        }
        return currentState;
    }
    

    public boolean isSolved(){
        return this.solved;
    }
    
    public void reportDeletion() {
        if (--deletionsUntilSolved == 0) {
            solved = true;
        }
    }

    public void initialize() {
        //We have 9 rows, 9 columns, 9 blocks
        rows = new RowColBlock[]{
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this),
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this),
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this)
                };

        cols = new RowColBlock[]{
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this),
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this),
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this)
                };

        blocks = new RowColBlock[]{
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this),
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this),
                    new RowColBlock(this), new RowColBlock(this), new RowColBlock(this)
                };

        //Every element is in one row one column and one block
        elements[0][0] = new Element(rows[0], cols[0], blocks[0]);
        elements[0][1] = new Element(rows[0], cols[1], blocks[0]);
        elements[0][2] = new Element(rows[0], cols[2], blocks[0]);
        elements[0][3] = new Element(rows[0], cols[3], blocks[1]);
        elements[0][4] = new Element(rows[0], cols[4], blocks[1]);
        elements[0][5] = new Element(rows[0], cols[5], blocks[1]);
        elements[0][6] = new Element(rows[0], cols[6], blocks[2]);
        elements[0][7] = new Element(rows[0], cols[7], blocks[2]);
        elements[0][8] = new Element(rows[0], cols[8], blocks[2]);
        elements[1][0] = new Element(rows[1], cols[0], blocks[0]);
        elements[1][1] = new Element(rows[1], cols[1], blocks[0]);
        elements[1][2] = new Element(rows[1], cols[2], blocks[0]);
        elements[1][3] = new Element(rows[1], cols[3], blocks[1]);
        elements[1][4] = new Element(rows[1], cols[4], blocks[1]);
        elements[1][5] = new Element(rows[1], cols[5], blocks[1]);
        elements[1][6] = new Element(rows[1], cols[6], blocks[2]);
        elements[1][7] = new Element(rows[1], cols[7], blocks[2]);
        elements[1][8] = new Element(rows[1], cols[8], blocks[2]);
        elements[2][0] = new Element(rows[2], cols[0], blocks[0]);
        elements[2][1] = new Element(rows[2], cols[1], blocks[0]);
        elements[2][2] = new Element(rows[2], cols[2], blocks[0]);
        elements[2][3] = new Element(rows[2], cols[3], blocks[1]);
        elements[2][4] = new Element(rows[2], cols[4], blocks[1]);
        elements[2][5] = new Element(rows[2], cols[5], blocks[1]);
        elements[2][6] = new Element(rows[2], cols[6], blocks[2]);
        elements[2][7] = new Element(rows[2], cols[7], blocks[2]);
        elements[2][8] = new Element(rows[2], cols[8], blocks[2]);
        elements[3][0] = new Element(rows[3], cols[0], blocks[3]);
        elements[3][1] = new Element(rows[3], cols[1], blocks[3]);
        elements[3][2] = new Element(rows[3], cols[2], blocks[3]);
        elements[3][3] = new Element(rows[3], cols[3], blocks[4]);
        elements[3][4] = new Element(rows[3], cols[4], blocks[4]);
        elements[3][5] = new Element(rows[3], cols[5], blocks[4]);
        elements[3][6] = new Element(rows[3], cols[6], blocks[5]);
        elements[3][7] = new Element(rows[3], cols[7], blocks[5]);
        elements[3][8] = new Element(rows[3], cols[8], blocks[5]);
        elements[4][0] = new Element(rows[4], cols[0], blocks[3]);
        elements[4][1] = new Element(rows[4], cols[1], blocks[3]);
        elements[4][2] = new Element(rows[4], cols[2], blocks[3]);
        elements[4][3] = new Element(rows[4], cols[3], blocks[4]);
        elements[4][4] = new Element(rows[4], cols[4], blocks[4]);
        elements[4][5] = new Element(rows[4], cols[5], blocks[4]);
        elements[4][6] = new Element(rows[4], cols[6], blocks[5]);
        elements[4][7] = new Element(rows[4], cols[7], blocks[5]);
        elements[4][8] = new Element(rows[4], cols[8], blocks[5]);
        elements[5][0] = new Element(rows[5], cols[0], blocks[3]);
        elements[5][1] = new Element(rows[5], cols[1], blocks[3]);
        elements[5][2] = new Element(rows[5], cols[2], blocks[3]);
        elements[5][3] = new Element(rows[5], cols[3], blocks[4]);
        elements[5][4] = new Element(rows[5], cols[4], blocks[4]);
        elements[5][5] = new Element(rows[5], cols[5], blocks[4]);
        elements[5][6] = new Element(rows[5], cols[6], blocks[5]);
        elements[5][7] = new Element(rows[5], cols[7], blocks[5]);
        elements[5][8] = new Element(rows[5], cols[8], blocks[5]);
        elements[6][0] = new Element(rows[6], cols[0], blocks[6]);
        elements[6][1] = new Element(rows[6], cols[1], blocks[6]);
        elements[6][2] = new Element(rows[6], cols[2], blocks[6]);
        elements[6][3] = new Element(rows[6], cols[3], blocks[7]);
        elements[6][4] = new Element(rows[6], cols[4], blocks[7]);
        elements[6][5] = new Element(rows[6], cols[5], blocks[7]);
        elements[6][6] = new Element(rows[6], cols[6], blocks[8]);
        elements[6][7] = new Element(rows[6], cols[7], blocks[8]);
        elements[6][8] = new Element(rows[6], cols[8], blocks[8]);
        elements[7][0] = new Element(rows[7], cols[0], blocks[6]);
        elements[7][1] = new Element(rows[7], cols[1], blocks[6]);
        elements[7][2] = new Element(rows[7], cols[2], blocks[6]);
        elements[7][3] = new Element(rows[7], cols[3], blocks[7]);
        elements[7][4] = new Element(rows[7], cols[4], blocks[7]);
        elements[7][5] = new Element(rows[7], cols[5], blocks[7]);
        elements[7][6] = new Element(rows[7], cols[6], blocks[8]);
        elements[7][7] = new Element(rows[7], cols[7], blocks[8]);
        elements[7][8] = new Element(rows[7], cols[8], blocks[8]);
        elements[8][0] = new Element(rows[8], cols[0], blocks[6]);
        elements[8][1] = new Element(rows[8], cols[1], blocks[6]);
        elements[8][2] = new Element(rows[8], cols[2], blocks[6]);
        elements[8][3] = new Element(rows[8], cols[3], blocks[7]);
        elements[8][4] = new Element(rows[8], cols[4], blocks[7]);
        elements[8][5] = new Element(rows[8], cols[5], blocks[7]);
        elements[8][6] = new Element(rows[8], cols[6], blocks[8]);
        elements[8][7] = new Element(rows[8], cols[7], blocks[8]);
        elements[8][8] = new Element(rows[8], cols[8], blocks[8]);
    }
}
