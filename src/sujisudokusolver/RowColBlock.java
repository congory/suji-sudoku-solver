/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sujisudokusolver;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yves
 */
public class RowColBlock {

    private Engine theEngine;
    private Element[] elements = new Element[9];
    private int index = 0;
    
    
    private boolean isFinal = false;
    private int[] missingNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int countMissingNumbers = 9;

    public RowColBlock(Engine engine) {
        this.theEngine = engine;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void addElement(Element toAdd){
        this.elements[index++] = toAdd;
    }
    
    public void runMethodOfElemination() {

        List<Element> elementsWithSearchedNo;

        for (int i = 0; i < 9; i++) {
            if (missingNumbers[i] != 0) {
                elementsWithSearchedNo = new LinkedList<Element>();
                for (int j = 0; j < 9; j++) {
                    if(elements[j].couldBeMissingNumber(i+1)){
                        elementsWithSearchedNo.add(elements[j]);
                    }
                }
                if(elementsWithSearchedNo.size() == 1){
                    elementsWithSearchedNo.get(0).setNumber(i + 1);
                }
                    
            }
        }
    }

    public void elementDeletion(int numberToDelete) {

        //nothing to delete any more
        if (this.isFinal()) {
            return;        //memorize deleted number
        }

        this.missingNumbers[numberToDelete - 1] = 0;
        if ((--countMissingNumbers) == 0) {
            this.isFinal = true;        //report to engine
        }
        theEngine.reportDeletion();

        //delete on every element
        for (int i = 0; i < 9; i++) {
            elements[i].delete(numberToDelete);
        }
    }
}
