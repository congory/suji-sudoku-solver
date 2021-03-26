/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sujisudokusolver;

/**
 *
 * @author Yves
 */
public class Element {

    private RowColBlock myRow;
    private RowColBlock myCol;
    private RowColBlock myBlock;
    
    private int[] possibilities = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int countElements = 9;
    
    private boolean isFinal = false;
    private int finalNumber;
            
    Element(RowColBlock row, RowColBlock col, RowColBlock block){
        this.myRow = row;
        this.myCol = col;
        this.myBlock = block;
        
        myRow.addElement(this);
        myCol.addElement(this);
        myBlock.addElement(this);
    }
    
    public boolean isFinal(){
        return isFinal;
    }
    
    public void setNumber(int finalNumber){
        
        //set finalNumber
        this.finalNumber = finalNumber;
        
        //mark as final
        this.isFinal = true;
        
        //trigger deletion of this number on other element
        this.myRow.elementDeletion(finalNumber);
        this.myCol.elementDeletion(finalNumber);
        this.myBlock.elementDeletion(finalNumber);
    }
    
    public String getNumber(){
        if(isFinal()){
            return String.valueOf(this.finalNumber);
        }
        return "";
    }
    
    public boolean couldBeMissingNumber(int number){
        return (!isFinal && possibilities[number -1] == number); // TEST
    }
    
    public void delete(int numberToDelete){
        //already final
        if( isFinal() ) return;
        //number already set
        if(possibilities[numberToDelete-1] == 0) return;
        
        //Set newly deleted number
        possibilities[numberToDelete-1] = 0;
        
        //Are we now final?
        if( (--countElements) == 1){
            for(int i = 0; i< 9; i++){
                if(possibilities[i] != 0){
                    this.setNumber(possibilities[i]);
                    return;
                }
            }
        }
    }
}
