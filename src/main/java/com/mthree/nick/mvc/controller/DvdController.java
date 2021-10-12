package com.mthree.nick.mvc.controller;

import com.mthree.nick.mvc.dao.DVD_DAO;
import com.mthree.nick.mvc.dto.DVD_DTO;

import java.util.*;

public class DvdController implements DVD_DAO {
    //add
    //remove
    //edit
    //list
    HashMap<String, DVD_DTO> DVDs = new HashMap<String, DVD_DTO>();

    public DvdController(HashMap<String, DVD_DTO> DVDs) {
        this.DVDs = DVDs;
    }

    @Override
    public HashMap<String, DVD_DTO> list() {
        for (String i: DVDs.keySet()) {
            System.out.println(i + "   -   " + DVDs.get(i).getSTUDIO());
        }
        return DVDs;
    }

    @Override
    public DVD_DTO findByTitle(String title) {
        return DVDs.get(title);
    }

    @Override
    public void displayInformation(String title) {
        try {
            DVD_DTO dvd = findByTitle(title);
            String info = dvd.getTITLE() + " - " + dvd.getSTUDIO();
            info += ", director: " + dvd.getDIRECTOR();
            info += ", " + dvd.getRELEASE_DATE();
            info += ", rated: " + dvd.getRatingMPAA();
            info += ", User review: " + dvd.getUserNote() + "; " + dvd.getUserRating();
            System.out.println(info);
        }
        catch (NullPointerException e) {
            System.out.println("DVD not found");
        }
    }

    @Override
    public HashMap<String, DVD_DTO> loadFromFile() {
        return null;
    }

    @Override
    public void saveToFile() {

    }

    @Override
    public boolean insertDVD(String title) {
        System.out.println("Input the DVD values, separated by commas. \n" +
                "(e.g. releasedate (dd/mm/yyyy),mpaaRating,director,studio,decimalrating,usernote");
        Scanner line = new Scanner(System.in);
        boolean valid = false;
        String rel = "", mpaa = "", dir = "", studio = "", note = "";
        double rating = 0;
        line.useDelimiter(",");
        line.useDelimiter(",|\\R");
        Scanner input = line;
        for (int i = 0; i < 6; i++) {
            do {
                valid = false;
                try {
                    switch (i){
                        case 0:
                            rel = input.next();
                            valid = true;
                            break;
                        case 1:
                            mpaa = input.next();
                            valid = true;
                            break;
                        case 2:
                            dir = input.next();
                            valid = true;
                            break;
                        case 3:
                            studio = input.next();
                            valid = true;
                            break;
                        case 4:
                            rating = input.nextDouble();
                            valid = true;
                            break;
                        case 5:
                            note = input.next();
                            valid = true;
                            break;
                    }
                }
                catch (InputMismatchException e ) {
                    System.out.println("Input valid value.");
                }
            } while (!valid);
        }

        System.out.println("Success! Added " + title + " to records");
        DVD_DTO dvd = new DVD_DTO(title, rel, mpaa, dir, studio, rating, note);

        DVDs.put(dvd.getTITLE(), dvd);
        return false;
    }

    @Override
    public boolean updateDVD(String title) {
        //find 
        //change 
        //commit
        DVD_DTO dvd = findByTitle(title);
        Scanner in = new Scanner(System.in);
        System.out.println("What would you like to edit? 1 - MPAA rating, 2 - User rating, " +
                "3 - User review");
        int choice =0;
        boolean isValid = false;
        do {
            try {
                choice = in.nextInt();
                if (choice <= 3 & choice >= 1) {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid number");
            }
        } while (isValid);

        isValid = false;
        String mpaa;
        double rating;
        String review;
        do {
            try {
                System.out.println("Enter the new value (rating is a decimal value," +
                        " reviews and MPAA ratings are strings):");

                switch (choice){
                    case 1:
                        mpaa = in.next();
                        dvd.setRatingMPAA(mpaa);
                        break;
                    case 2:
                        rating = in.nextDouble();
                        dvd.setUserRating(rating);
                        isValid = true;
                        break;
                    case 3:
                        review = in.next();
                        dvd.setUserNote(review);
                        isValid = true;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid value.");
            }
        } while (isValid);

        System.out.println("Commit changes? (y/n - case sensitive)");
        String yOrN = in.next();
        if (yOrN.equals("y")) {
            DVD_DTO toRemove = findByTitle(dvd.getTITLE());
            DVDs.remove(toRemove);
            DVDs.put(dvd.getTITLE(), dvd);

            System.out.println();
        }
        else {
            System.out.println("Returning to menu.");
        }
        return true;
    }

    @Override
    public boolean deleteDVD(String title) {
        try {
            DVD_DTO dvd = findByTitle(title);
            DVDs.remove(dvd);
            System.out.println("\'" + dvd.getTITLE() + "\' has been removed.");
            return true;
        } catch (NullPointerException e) {
            System.out.println(title + " was not in the collection.");
            return false;
        }
    }
}
