import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map myMap = new Map();

        System.out.println("Othello Game");
        System.out.println("By Mahdi Hejrati 9723100\n");
        System.out.println("1. Single player");
        System.out.println("2. Double player");
        int kind = input.nextInt();

        if (kind == 2) {
            System.out.println("Enter first player name (black color):");
            Player p1 = new Player(input.next(), 1);
            System.out.println("Enter second player name (white color):");
            Player p2 = new Player(input.next(), 2);
            System.out.println("Game starts");
            System.out.println("Please Choose your block in \"I C form\" (I: an integer for row, C: a char in capital for column)");
            myMap.print(p1, p2);

            while (true) {
                // this method show where he can put his disk
                //myMap.validBlock(p1);
                //p1.print();
                playing(input, myMap, p1, p2);
                //myMap.validBlock(p2);
                //p2.print();
                playing(input, myMap, p2, p1);
            }
        }
    }

    /**
     * this method is written to avoid code duplication
     * @param input in the method we get our inputs
     * @param myMap map of the game
     * @param firstPlayer first player
     * @param secondPlayer second player
     */
    private static void playing(Scanner input, Map myMap, Player firstPlayer, Player secondPlayer) {
        System.out.println(firstPlayer.getName() + " turn:");
        // if player 1 can put his disk anywhere
        if (myMap.hasValid(firstPlayer)) {
            // get an integer and character
            while (!myMap.play(firstPlayer, input.nextInt() - 1, (int) input.next().charAt(0) - 64 - 1)) ;
            myMap.print(firstPlayer, secondPlayer);
         // finish the game if none of the player cant put disk.
        }else if (!myMap.hasValid(secondPlayer)) {
            System.out.println("Finish");
            myMap.print(firstPlayer, secondPlayer);
            if (myMap.countColor(firstPlayer) > myMap.countColor(secondPlayer))
                System.out.println("*" + firstPlayer.getName() + " win*");
            else if (myMap.countColor(firstPlayer) < myMap.countColor(secondPlayer))
                System.out.println("*" + secondPlayer.getName() + " win*");
            else
                System.out.println("Draw");
        } else
            System.out.println("You cant place disk. Pass");
    }
}
