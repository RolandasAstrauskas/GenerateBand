package com.company.band;

public class Band {

    private int id;
    private String nameAdjective;
    private String nameNoun;
    private String drummer;
    private String vocalist;
    private String bassGuitarist;
    private String electricGuitarist;

    public Band(int id, String nameAdjective, String nameNoun, String drummer, String vocalist, String bassGuitarist, String electricGuitarist) {
        this.id = id;
        this.nameAdjective = nameAdjective;
        this.nameNoun = nameNoun;
        this.drummer = drummer;
        this.vocalist = vocalist;
        this.bassGuitarist = bassGuitarist;
        this.electricGuitarist = electricGuitarist;
    }

    public Band(String nameAdjective, String nameNoun, String drummer, String vocalist, String bassGuitarist, String electricGuitarist) {
        this.nameAdjective = nameAdjective;
        this.nameNoun = nameNoun;
        this.drummer = drummer;
        this.vocalist = vocalist;
        this.bassGuitarist = bassGuitarist;
        this.electricGuitarist = electricGuitarist;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAdjective() {
        return nameAdjective;
    }

    public void setNameAdjective(String nameAdjective) {
        this.nameAdjective = nameAdjective;
    }

    public String getNameNoun() {
        return nameNoun;
    }

    public void setNameNoun(String nameNoun) {
        this.nameNoun = nameNoun;
    }

    public String getDrummer() {
        return drummer;
    }

    public void setDrummer(String drummer) {
        this.drummer = drummer;
    }

    public String getVocalist() {
        return vocalist;
    }

    public void setVocalist(String vocalist) {
        this.vocalist = vocalist;
    }

    public String getBassGuitarist() {
        return bassGuitarist;
    }

    public void setBassGuitarist(String bassGuitarist) {
        this.bassGuitarist = bassGuitarist;
    }

    public String getElectricGuitarist() {
        return electricGuitarist;
    }

    public void setElectricGuitarist(String electricGuitarist) {
        this.electricGuitarist = electricGuitarist;
    }

    @Override
    public String toString() {
        return   "\n" + getNameAdjective() + " " +
                getNameNoun() + "!!!"+
                " Vocalist " + getVocalist() +
                ", drummer " + getDrummer() +
                ", bass guitarist " + getBassGuitarist() +
                ", electric guitarist " + getElectricGuitarist() + "\n";
    }
}
