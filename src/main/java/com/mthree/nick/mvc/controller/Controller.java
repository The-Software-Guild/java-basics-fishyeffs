package com.mthree.nick.mvc.controller;

import com.mthree.nick.mvc.model.DVD_DAO;
import com.mthree.nick.mvc.model.DVD_DTO;

import java.util.List;

public class Controller implements DVD_DAO {
    //add
    //remove
    //edit
    //list
    List<DVD_DTO> DVDs;

    @Override
    public List<DVD_DTO> list() {
        for (DVD_DTO dvd: DVDs) {
            System.out.println(dvd.getTITLE() + "   -   " + dvd.getSTUDIO());
        }
        return DVDs;
    }

    @Override
    public DVD_DTO findByTitle(String title) {
        for (DVD_DTO dvd: DVDs) {
            if (dvd.getTITLE().equals(title)) {
                return dvd;
            }
        }
        return null;
    }

    @Override
    public List<DVD_DTO> displayInformation(String title) {

        return null;
    }

    @Override
    public List<DVD_DTO> loadFromFile() {
        return null;
    }

    @Override
    public void saveToFile() {

    }

    @Override
    public boolean insertDVD(DVD_DTO dvd) {
        DVDs.add(dvd);
        return false;
    }

    @Override
    public boolean updateDVD(String title, DVD_DTO dvd) {
        return false;
    }

    @Override
    public boolean deleteDVD(DVD_DTO dvd) {
        return false;
    }

    @Override
    public boolean contains() {
        return false;
    }
}
