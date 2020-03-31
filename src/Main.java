import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map myMap = new Map();

        System.out.println("Othello");
        System.out.println("1. Single player");
        System.out.println("2. Multi player");
        int kind = input.nextInt();

        if (kind == 2) {
            System.out.println("Enter first player name:");
            Player p1 = new Player(input.next(), 2);
            System.out.println("Enter second player name1:");
            Player p2 = new Player(input.next(), 1);
            System.out.println("Game starts");
            myMap.print();
            
  //          do {
                System.out.println(p1.getName() + " turn");
                // get an integer and character
                myMap.play(p1, input.nextInt() - 1, (int) input.next().charAt(0) - 64 - 1);

            // harkat momken sefr nabashe
//            } while (true);
        }


    }
}
