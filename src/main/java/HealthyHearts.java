import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        System.out.print("What is your age? ");
        Scanner in = new Scanner(System.in);
        int age = in.nextInt();

        int max = (220 - age);
        int minHR, maxHR;
        minHR = (int) Math.round(0.5 * max);
        maxHR = (int) Math.round(0.85 * max);

        System.out.println("Your maximum heart rate should be "+ max + " beats per minute" );
        System.out.println("Your target HR Zone is " + minHR + " - " + maxHR + " beats per minute");
    }
}
