import javax.swing.*;

public class Map {

    private int[][] map;

    public Map() {
        map = new int[8][8];
        map[4][3] = 1;
        map[3][4] = 1;
        map[3][3] = 2;
        map[4][4] = 2;
        map[5][5] = 1;
        map[6][6] = 2;
    }

    public int[][] getMap() {
        return map;
    }

    /**
     * this method get the player and record the block that it can place his disk.
     *
     * @param player player to check
     */
    public void validBlock(Player player) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                player.resetValidBlock(i, j);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (checkAround(i, j, player.getColorCode()))
                    player.setValidBlock(i, j);
    }

    /**
     * this method get an specific block and check all around it.
     *
     * @param i         row number of block
     * @param j         column number of block
     * @param colorCode the color to check
     * @return true if that block is valid or false if not
     */
    public boolean checkAround(int i, int j, int colorCode) {
        if (map[i][j] == 0) {
            if (checkDirection(-1, 1, i, j, colorCode))
                return true;
            if (checkDirection(0, 1, i, j, colorCode))
                return true;
            if (checkDirection(1, 1, i, j, colorCode))
                return true;
            if (checkDirection(1, 0, i, j, colorCode))
                return true;
            if (checkDirection(1, -1, i, j, colorCode))
                return true;
            if (checkDirection(0, -1, i, j, colorCode))
                return true;
            if (checkDirection(-1, -1, i, j, colorCode))
                return true;
            if (checkDirection(-1, 0, i, j, colorCode))
                return true;
        }
        return false;
    }

    /**
     * this method get a block and two parameter to check an specific direction around it
     *
     * @param k         it makes new row
     * @param l         it makes new column
     * @param i         row of block
     * @param j         column of block
     * @param colorCode color to check
     * @return true if it is possible and false if not.
     */
    public boolean checkDirection(int k, int l, int i, int j, int colorCode) {
        int i2 = i + k, j2 = j + l;
        if (isInMap(i2, j2)) {
            while ((map[i2][j2] != 0) && (map[i2][j2] != colorCode)) {
                i2 = i2 + k;
                j2 = j2 + l;
                if (isInMap(i2, j2)) {
                    if (map[i2][j2] == colorCode)
                        return true;
                } else
                    return false;
            }
        }
        return false;
    }

    /**
     * this method check that if the block [i][j] is in our map or not
     *
     * @param i row
     * @param j column
     * @return true if in map and false if not
     */
    public boolean isInMap(int i, int j) {
        if (((i >= 0) && (i <= 7)) && ((j >= 0) && (j <= 7)))
            return true;
        else
            return false;
    }

    public boolean play(Player player, int row, int column) {
        validBlock(player);
        if (player.getValidBlocks()[row][column] == 1) {
            map[row][column] = player.getColorCode();
            reverseAround(player.getColorCode(), row, column);
            return true;
        } else {
            System.out.println("Impossible, Please choose another block");
            return false;
        }
    }

    public void reverseAround(int colorCode, int i, int j) {
        reverseDirection(-1, 1, i, j, colorCode);
        reverseDirection(0, 1, i, j, colorCode);
        reverseDirection(1, 1, i, j, colorCode);
        reverseDirection(1, 0, i, j, colorCode);
        reverseDirection(1, -1, i, j, colorCode);
        reverseDirection(0, -1, i, j, colorCode);
        reverseDirection(-1, -1, i, j, colorCode);
        reverseDirection(-1, 0, i, j, colorCode);

    }

    public void reverseDirection(int k, int l, int i, int j, int colorCode) {
        boolean flag = checkDirection(k, l, i, j, colorCode);
        if (flag) {
            do {
                map[i][j] = colorCode;
                i += k;
                j += l;
            } while (map[i][j] != colorCode);
        }
    }

    public boolean hasValid(Player player) {
        validBlock(player);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (player.getValidBlocks()[i][j] == 1)
                    return true;
        return false;
    }

    public int countColor (Player player) {
        int sum = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (map[i][j] == player.getColorCode())
                    sum++;
        return sum;
    }

    // in fact we save three number 0, 1, 2 in map, but in printing use unicode
    public void print(Player p1, Player p2) {
        // print A to H character
        System.out.print("   ");
        for (int k = 65 ; k <= 72; k++)
            System.out.print((char) k + " ");
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == 1)
                    System.out.print('\u25cf' + " ");
                else if (map[i][j] == 2)
                    System.out.print('\u25cc' + " ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.print(p1.getName() + ": " + countColor(p1));
        System.out.println("    " + p2.getName() + ": " + countColor(p2));
    }
}
