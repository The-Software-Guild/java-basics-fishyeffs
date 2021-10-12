package com.mthree.nick.mvc.controller;

import com.mthree.nick.mvc.dao.DVD_DAO;
import com.mthree.nick.mvc.dto.DVD_DTO;

import java.io.*;
import java.util.*;

public class DvdController implements DVD_DAO {
    //add
    //remove
    //edit
    //list
    private final String FILENAME = "dvd.txt";
    private HashMap<String, DVD_DTO> DVDs = new HashMap<String, DVD_DTO>();

    public DvdController(HashMap<String, DVD_DTO> DVDs) {
        this.DVDs = DVDs;
    }

    @Override
    public HashMap<String, DVD_DTO> list() {
        if (DVDs.size() == 0) {
            System.out.println("No titles exist in the list.");
        }
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
    public void loadFromFile() {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            while (sc.hasNextLine()) {
                //String line = sc.nextLine();
                DVD_DTO dvdTemp = unmarshall(sc);
                this.DVDs.put(dvdTemp.getTITLE(), dvdTemp);
            }
            System.out.println("Written " + this.DVDs.size() + " DVD(s) from " + FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DVD_DTO unmarshall(Scanner line) {
        String title, relDate,mpaa,dir,studio,uNote;
        line.useDelimiter(",|\\R");
        double uRating;
        try {
            title = line.next();
            relDate = line.next();
            mpaa = line.next();
            dir = line.next();
            studio = line.next();
            uRating = line.nextDouble();
            uNote = line.next();
            return new DVD_DTO(title, relDate, mpaa, dir, studio , uRating, uNote);
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid file format.");
            return null;
        }
    }

    @Override
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILENAME));
            String [] contents = new String[DVDs.size()];
            int j = 0;
            for (String i : DVDs.keySet()) {
                contents[j] = marshallData(DVDs.get(i));
                j++;
            }
            for (int i = contents.length - 1; i >= 0; i--) {
                if (i == 0) {
                    out.print(contents[i]);
                }
                else {
                    out.println(contents[i]);
                }
            }
            out.flush();
            out.close();
            System.out.println("Written " + DVDs.size() + " DVD(s) to " + FILENAME);
            this.DVDs.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String marshallData(DVD_DTO dvd) {
        String data = dvd.getTITLE() + ",";
        data += dvd.getRELEASE_DATE() + ",";
        data += dvd.getRatingMPAA() + ",";
        data += dvd.getDIRECTOR() + ",";
        data += dvd.getSTUDIO() + ",";
        data += dvd.getUserRating() + ",";
        data += dvd.getUserNote();
        //ddmmyyyy,rating,dir,studio,13.3,note
        return data;
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
        Scanner input;
        for (int i = 0; i < 6; i++) {
            do {
                input = line;
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
                    valid = false;
                    System.out.println("Please input the MPAA and review values, " +
                            "each on a new line.");
                    line = new Scanner(System.in);
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
                if (choice <= 3 && choice >= 1) {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid number");
            }
        } while (!isValid);

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
                        isValid = true;
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
        } while (!isValid);

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
            DVDs.remove(title);
            System.out.println("\'" + title + "\' has been removed.");
            return true;
        } catch (NullPointerException e) {
            System.out.println(title + " was not in the collection.");
            return false;
        }
    }
}
