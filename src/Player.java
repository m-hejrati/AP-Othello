import java.util.ArrayList;

public class Player {

    private String name;
    private int colorCode;
    // black: 1, white: 2;
    private int [][] validBlocks;

    public Player(String name, int colorCode) {
        this.name = name;
        this.colorCode = colorCode;
        validBlocks = new int[8][8];
    }

    public String getName() {
        return name;
    }

    public int getColorCode() {
        return colorCode;
    }

    public int[][] getValidBlocks() {
        return validBlocks;
    }

    public void setValidBlock (int i, int j) {
        validBlocks [i][j] = 1;
    }

    public void resetValidBlock (int i, int j) {
        validBlocks [i][j] = 0;
    }

    public void print() {
        for (int i = 0; i < 8; i++) {
            System.out.print("   ");
            for (int j = 0; j < 8; j++)
                System.out.print(validBlocks[i][j] + " ");
            System.out.println();
        }
    }
}
