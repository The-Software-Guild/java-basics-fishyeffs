import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DogGenetics {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;

        System.out.print("What is your dog's name? ");
        name = in.nextLine();

        System.out.println("Well then, I have this highly reliable report on "+ name + "'s prestigious background right here.\n");
        System.out.println(name + " is:\n");

        String[] breeds = {"Alastian","Spaniel","Border Collie","Airedale","Bloodhound"};

        int percentageLeft = 100;
        int total = 0;
        for (int i = 0; i < breeds.length; i++) {
            Random r = new Random();
            int percent = 0;
            if (i == breeds.length - 1) {
                percent = percentageLeft;
            }
            else {
                percent = r.nextInt(101 - (100 - percentageLeft));
            }
            total += percent;
            System.out.println(percent + "% " + breeds[i]);
            percentageLeft -= percent;
        }
    }
}
