import java.util.Random;

public class Game {
    private final int rounds;

    public Game(int rounds) {
        this.rounds = rounds;
    }

    public int round(int p1Move) {
        Random r = new Random();
        int winner;
        int p2Move = r.nextInt(3-1) + 1;

        switch (p1Move) {
            case 1:
                System.out.println("You used rock.");
                break;
            case 2:
                System.out.println("You used paper.");
                break;
            case 3:
                System.out.println("You used scissors.");
                break;
        }

        switch (p2Move) {
            case 1:
                System.out.println("Player 2 used rock.");
                break;
            case 2:
                System.out.println("Player 2 used paper.");
                break;
            case 3:
                System.out.println("Player 2 used scissors.");
                break;
        }

        return roundResult(p1Move, p2Move);
    }

    /*
     * returns 0 : tie
     * returns 1: p1 wins
     * returns 2: p2 wins
     */
    private int roundResult(int p1Move, int p2Move) {
        int winner = 0;

        //1 - rock
        //2 - paper
        //3 - scissors
        if (p1Move == 1 && p2Move == 3) {
            winner = 1;
            System.out.println("You win!");
        }
        else if (p1Move == 2 && p2Move == 1) {
            winner = 1;
            System.out.println("You win!");
        }
        else if (p1Move == 3 && p2Move == 2) {
            winner = 1;
            System.out.println("You win!");
        }
        else if (p1Move == p2Move) {
            winner = 0;
            System.out.println("Tie!");
        }
        else {
            winner = 2;
            System.out.println("You lose!");
        }

        return winner;
    }

    public int getRounds() {
        return rounds;
    }
}
