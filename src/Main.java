/**
 * this class run the game
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.03.31
 */
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map myMap = new Map();

        System.out.println("Othello Game");
        System.out.println("By Mahdi Hejrati 9723100\n");
        System.out.println("1. Single player");
        System.out.println("2. Double player");

        int kind = input.nextInt();

        if (kind == 1) {
            System.out.println("Enter your name (black color):");
            Player p1 = new Player(input.next(), 1);

            Player p2 = new Player("Computer", 2);

            System.out.println("Game starts");
            System.out.println("Please Choose your block in \"I C form\" (I: an integer for row, C: a char in capital for column)");

            myMap.print(p1, p2);

            while (true) {
                // this method show where he can put his disk
                //myMap.validBlock(p1);
                //p1.print();
                if (!playing(input, myMap, p1, p2))
                    break;
                //myMap.validBlock(p2);
                //p2.print();
                if (!playing(input, myMap, p2, p1))
                    break;
            }
        } else if (kind == 2) {
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
                if (!playing(input, myMap, p1, p2))
                    break;
                //myMap.validBlock(p2);
                //p2.print();
                if (!playing(input, myMap, p2, p1))
                    break;
            }
        }
    }

    /**
     * this method is written to avoid code duplication
     *
     * @param input in the method we get our inputs
     * @param myMap map of the game
     * @param firstPlayer  first player
     * @param secondPlayer second player
     */
    private static boolean playing(Scanner input, Map myMap, Player firstPlayer, Player secondPlayer) {
        boolean flag = true;
        System.out.println(firstPlayer.getName() + " turn:");
        // if player 1 can put his disk anywhere
        if (myMap.hasValid(firstPlayer)) {
            // get an integer and character
            if (firstPlayer.getName().equals("Computer")) {
                Random rnd = new Random();
                int row, column;
                while (!myMap.play(firstPlayer, row = rnd.nextInt(8), column = rnd.nextInt(8))) ;
                System.out.println((row + 1) + " " + (char) (column + 65));
                myMap.print(firstPlayer, secondPlayer);
            } else {
                // enter block until it be correct
                while (!myMap.play(firstPlayer, input.nextInt() - 1, (int) input.next().charAt(0) - 64 - 1)) ;
                myMap.print(firstPlayer, secondPlayer);
            }
            // finish the game if none of the player cant put disk.
        } else if (!myMap.hasValid(secondPlayer)) {
            System.out.println("Finish");
            myMap.print(firstPlayer, secondPlayer);
            // print the winner of the game
            if (myMap.countColor(firstPlayer) > myMap.countColor(secondPlayer))
                System.out.println("*" + firstPlayer.getName() + " win*");
            else if (myMap.countColor(firstPlayer) < myMap.countColor(secondPlayer))
                System.out.println("*" + secondPlayer.getName() + " win*");
            else
                System.out.println("Draw");
            flag = false;
        } else
            System.out.println("You cant place disk. Pass");
    if (flag)
        return true;
    else
        return false;
    }
}