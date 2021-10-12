package com.mthree.nick.mvc.ui;

import com.mthree.nick.mvc.controller.DvdController;
import com.mthree.nick.mvc.dto.DVD_DTO;

import java.util.*;

public class View {
    public static void main(String[] args) {
        HashMap<String, DVD_DTO> DVDs= new HashMap<String, DVD_DTO>();
        DvdController cont = new DvdController(DVDs);
        Scanner in = new Scanner(System.in);


        int choice = 0;
        boolean exit = false;
        do {
            try {
                System.out.println("Enter an option: ");
                System.out.println("0 - exit");
                System.out.println("1 - list");
                System.out.println("2 - find by title");
                System.out.println("3 - display singular dvd");
                System.out.println("4 - load from file");
                System.out.println("5 - save to file");
                System.out.println("6 - insert");
                System.out.println("7 - update");
                System.out.println("8 - delete");
                choice = in.nextInt();

                if (choice < 9 && choice > 0) {

                }
                else {
                    System.out.println("Enter a number between 1 and 8.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Enter a number between 1 and 8.");
            }

            System.out.println("Choice: " + choice);
            switch (choice) {
                case 1:
                    cont.list();
                    break;
                case 4:
                    cont.loadFromFile();
                    break;
                case 5:
                    cont.saveToFile();
                    break;
                case 2:
                    System.out.println("Input title");
                    String title = in.next();
                    try {
                        cont.findByTitle(title);
                        cont.displayInformation(title);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Does not exist.");
                    }
                    break;
                case 3:
                    System.out.println("Input title");
                    title = in.next();
                    cont.displayInformation(title);
                    break;
                case 6:
                    System.out.println("Input title");
                    title = in.next();
                    cont.insertDVD(title);
                    break;
                case 7:
                    System.out.println("Input title");
                    title = in.next();
                    cont.updateDVD(title);
                    break;
                case 8:
                    System.out.println("Input title");
                    title = in.next();
                    cont.deleteDVD(title);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
            }
            System.out.println("Press enter to continue...\n");
            in.nextLine();
            in.nextLine();
        } while (!exit);
    }
}

