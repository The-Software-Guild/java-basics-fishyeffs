import java.util.Random;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        String cont = "Yes";
        do {
            play();
            System.out.println("Continue playing? (Yes/No)");
            Scanner in = new Scanner(System.in);
            cont = in.next();

        } while (cont.equals("Yes"));

        System.out.println("Thanks for playing!");
    }

    public static void play() {
        boolean validRounds = false;
        int rounds = 0;
        do {
            try {
                System.out.println("Input number of rounds (1-10): ");
                Scanner in = new Scanner(System.in);
                rounds = in.nextInt();
                if (rounds <= 10 && rounds >= 1) {
                    validRounds = true;
                }
                else {
                    System.out.println("Please stay within the range.");
                }
            }
            catch (Exception e) {
                System.out.println("That was not a whole number!");
            }
        } while (!validRounds);

        System.out.printf("Rounds to play: %d \n", rounds);
        int winner = game(rounds);

        if (winner == 0) {
            System.out.println("The game is a draw!");
        }
        else if (winner == 1) {
            System.out.println("Player one wins the game!");
        }
        else {
            System.out.println("Player two wins the game!");
        }
    }

    public static int game(int rounds) {
        //Random r = new Random(3);
        int playerOneWin = 0;
        int p1Wins, p2Wins, ties, result;
        p1Wins = 0;
        p2Wins = 0;
        ties = 0;
        result = 0;

        Game game1 = new Game(rounds);

        for (int i = 0; i < rounds; i++) {
            System.out.println("Make your move...... (1 - Rock, 2 - Paper, 3 - Scissors)");
            Scanner in = new Scanner(System.in);
            int p1Move = in.nextInt();
            result = game1.round(p1Move);

            if (result == 1) {
                p1Wins += 1;
            }
            else if (result == 2) {
                p2Wins += 1;
            }
            else {
                ties += 1;
            }
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Player one wins: " + p1Wins);
        System.out.println("Player two wins: " + p2Wins);
        System.out.println("Ties: " + ties);
        if (p1Wins == p2Wins) {
            return 0;
        }
        else if (p1Wins > p2Wins) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
