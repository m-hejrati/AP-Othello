public class Map {

    int[][] map;


    public Map() {
        map = new int[8][8];
        map [3][3] = 1;
        map [4][4] = 1;
        map [4][3] = 2;
        map [3][4] = 2;
    }

    public void play (Player player, int row, int column) {
        System.out.println(row);
        System.out.println(column);
    }



    // in fact we save three number 0, 1, 2 in map, but in printing use unicode
    public void print() {
        // print A to H character
        System.out.print("   ");
        for (int k = 65 ; k <= 72; k++)
            System.out.print((char) k + " ");
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == 1)
                    System.out.print('\u25cb' + " ");
                else if (map[i][j] == 2)
                    System.out.print('\u25cf' + " ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
