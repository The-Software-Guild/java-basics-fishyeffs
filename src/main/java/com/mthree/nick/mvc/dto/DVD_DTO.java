package com.mthree.nick.mvc.dto;

public class DVD_DTO {
    private final String TITLE;
    private final String RELEASE_DATE;
    private String ratingMPAA;
    private final String DIRECTOR;
    private final String STUDIO;
    private double userRating;
    private String userNote;

    public DVD_DTO(String TITLE, String RELEASE_DATE, String ratingMPAA, String DIRECTOR, String STUDIO,
                   double userRating, String userNote) {
        this.TITLE = TITLE;
        this.RELEASE_DATE = RELEASE_DATE;
        this.ratingMPAA = ratingMPAA;
        this.DIRECTOR = DIRECTOR;
        this.STUDIO = STUDIO;
        this.userRating = userRating;
        this.userNote = userNote;
    }

    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getRELEASE_DATE() {
        return RELEASE_DATE;
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public String getDIRECTOR() {
        return DIRECTOR;
    }

    public String getSTUDIO() {
        return STUDIO;
    }

    public double getUserRating() {
        return userRating;
    }

    public String getUserNote() {
        return userNote;
    }
}
