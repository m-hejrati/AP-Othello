/**
 * this class holds information of player
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.03.31
 */

public class Player {

    private String name;
    // black: 1, white: 2;
    private int colorCode;
    // this array hold blocks that he can put his disk
    private int [][] validBlocks;

    /**
     * constructor for player
     * @param name name of the player
     * @param colorCode code of his color
     */
    public Player(String name, int colorCode) {
        this.name = name;
        this.colorCode = colorCode;
        validBlocks = new int[8][8];
    }

    /**
     * getter for player name
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for color code
     * @return color code
     */
    public int getColorCode() {
        return colorCode;
    }

    /**
     * getter for valid blocks
     * @return valid blocks
     */
    public int[][] getValidBlocks() {
        return validBlocks;
    }

    /**
     * setter for valid blocks.
     * it set the block that have condition to one
     * @param i row that should set
     * @param j column that should set
     */
    public void setValidBlock (int i, int j) {
        validBlocks [i][j] = 1;
    }

    /**
     * setter for valid blocks.
     * it set the block to zero
     * @param i row that should set
     * @param j column that should set
     */
    public void resetValidBlock (int i, int j) {
        validBlocks [i][j] = 0;
    }

    /**
     * print valid blocks
     */
    public void print() {
        for (int i = 0; i < 8; i++) {
            System.out.print("   ");
            for (int j = 0; j < 8; j++)
                System.out.print(validBlocks[i][j] + " ");
            System.out.println();
        }
    }
}