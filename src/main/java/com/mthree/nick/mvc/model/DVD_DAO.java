package com.mthree.nick.mvc.model;

import java.util.List;

public interface DVD_DAO {
    List<DVD_DTO> list();
    DVD_DTO findByTitle(String title);
    List<DVD_DTO> displayInformation(String title);
    List<DVD_DTO> loadFromFile();
    void saveToFile();
    boolean insertDVD(DVD_DTO dvd);
    boolean updateDVD(String title, DVD_DTO dvd);
    boolean deleteDVD(DVD_DTO dvd);
    boolean contains();
}
