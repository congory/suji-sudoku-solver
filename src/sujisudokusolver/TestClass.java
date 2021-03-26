/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sujisudokusolver;

/**
 *
 * @author Yves
 */
public class TestClass {

    public static void main(String[] args) {
        Engine engine = new Engine();
        long start = System.currentTimeMillis();
        engine.setInitialState(new String[][]{
                    {null, "3", "7", "9", null, null, null, null, null},
                    {null, null, null, null, null, "2", "7", null, "4"},
                    {null, "8", null, null, null, null, null, null, "2"},
                    {null, "9", null, "6", "1", "8", null, null, "7"},
                    {null, null, null, "7", "5", "3", null, null, null},
                    {"1", null, null, "2", "9", "4", null, "8", null},
                    {"7", null, null, null, null, null, null, "6", null},
                    {"4", null, "9", "3", null, null, null, null, null},
                    {null, null, null, null, null, "6", "1", "3", null}
                });
        System.out.println("Time needed to solve: " + ((System.currentTimeMillis() - start)) + " milliseconds.");


        System.out.println(engine.isSolved());

        String[][] result = engine.getCurrentState();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {

                System.out.print(result[i][j] + "\t");

            }
            System.out.println("\n");
        }
    }
}
