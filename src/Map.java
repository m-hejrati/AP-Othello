/**
 * this is the major class that hold the information of map
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.03.31
 */
public class Map {

    private int[][] map;

    /**
     * create the map
     */
    public Map() {
        map = new int[8][8];
        map[4][3] = 1;
        map[3][4] = 1;
        map[3][3] = 2;
        map[4][4] = 2;
    }

    /**
     * this method control other blocks
     * @param player player to play
     * @param row the entered row
     * @param column the entered column
     * @return true if he entered a valid block and false if not
     */
    public boolean play(Player player, int row, int column) {
        // update valid blocks
        validBlock(player);
        // if he entered correct, set his color to block and reverse all disk between them.
        if (player.getValidBlocks()[row][column] == 1) {
            map[row][column] = player.getColorCode();
            reverseAround(player.getColorCode(), row, column);
            return true;
        } else {
            if (!player.getName().equals("Computer"))
                System.out.println("Impossible, Please choose another block");
            return false;
        }
    }

    /**
     * this method get the player and save the block that it can place his disk.
     * @param player player to check
     */
    public void validBlock(Player player) {
        // first set all of them to zero
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                player.resetValidBlock(i, j);

        // then check all the block of map to find valid
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (checkAround(i, j, player.getColorCode()))
                    player.setValidBlock(i, j);
    }

    /**
     * this method get an specific block and check all around it.
     * @param i row number of block
     * @param j column number of block
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
     * @param k its added to make new row
     * @param l its added to make new column
     * @param i row of block
     * @param j column of block
     * @param colorCode color to check
     * @return true if it is possible and false if not.
     */
    public boolean checkDirection(int k, int l, int i, int j, int colorCode) {
        int i2 = i + k, j2 = j + l;
        // check if it is in the map or not
        if (isInMap(i2, j2)) {
            // if its color for another player
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

    /**
     * reverse all disks that has the condition
     * @param colorCode color code of putted disk
     * @param i row number of putted disk
     * @param j column number of putted disk
     */
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

    /**
     * reverse disks in an specific direction
     * @param k its added to make new row
     * @param l its added to makes new column
     * @param i row number of putted disk
     * @param j column number of putted disk
     * @param colorCode color code of putted disk
     */
    public void reverseDirection(int k, int l, int i, int j, int colorCode) {
        // check if this direction has condition or not
        boolean flag = checkDirection(k, l, i, j, colorCode);
        if (flag) {
            // reverse all disk in the way until we arrive to our color
            do {
                map[i][j] = colorCode;
                i += k;
                j += l;
            } while (map[i][j] != colorCode);
        }
    }

    /**
     * check if the player can put any disk or not
     * @param player player to check
     * @return true if he can and false if he cant
     */
    public boolean hasValid(Player player) {
        // update valid blocks
        validBlock(player);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (player.getValidBlocks()[i][j] == 1)
                    return true;
        return false;
    }

    /**
     * count disks of player in the map
     * @param player player to count
     * @return number of his disk
     */
    public int countColor (Player player) {
        int sum = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (map[i][j] == player.getColorCode())
                    sum++;
        return sum;
    }

    /**
     * print the map and number of each persons disk
     * we save three number 0, 1, 2 in map, but in printing use unicode
     * @param p1 first player
     * @param p2 second player
     */
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
        // print online result in the match
        System.out.print(p1.getName() + ": " + countColor(p1));
        System.out.println("    " + p2.getName() + ": " + countColor(p2));
    }
}