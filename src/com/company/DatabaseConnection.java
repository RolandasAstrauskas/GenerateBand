package com.company;

import com.company.band.Band;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private final String TIME_ZONE = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String URL = "jdbc:mysql://localhost:3306/banddatabase?";
    private final String USER = "";
    private final String PASS = "";

    public void saveBand(String nameAdjective, String nameNoun, String drummer, String vocalist, String bassGuitarist, String electricGuitarist)  {

        try {
            Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `banddatabase`.`band` (`id`, `nameAdjective`, `nameNoun`," +
                    " `drummer`, `vocalist`, `bassGuitarist`, `electricGuitarist`)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, generateIdBand());
            preparedStatement.setString(2, nameAdjective);
            preparedStatement.setString(3, nameNoun);
            preparedStatement.setString(4, drummer);
            preparedStatement.setString(5, vocalist);
            preparedStatement.setString(6, bassGuitarist);
            preparedStatement.setString(7, electricGuitarist);
            preparedStatement.executeUpdate();
            conn.close();
        }catch (SQLException e){
            System.out.println("Can't save band, problem with database");
        }


    }

    public List<Band> bandList(){
                List<Band> bandList = new ArrayList<>();

                try{
                Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM band;");
                ResultSet rs = statement.executeQuery();

                while(rs.next()) {
                    bandList.add(new Band(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)));
                }}catch (SQLException e){
                    System.out.println("Can't create band list, problem with database");
                }
                return bandList;
    }

    public void saveNoun(String noun) {
        try{
                Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
                 PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `banddatabase`.`noun` (`id`, `noun`) VALUES (?, ?)");
                 preparedStatement.setInt(1, generateIdNoun());
                 preparedStatement.setString(2, noun);
                 preparedStatement.executeUpdate();
                conn.close();
        }catch (SQLException e) {
            System.out.println("Can't save noun, problem with database");
        }
    }


    public void saveAdjective(String adjective){
        try {
            Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `banddatabase`.`adjective` (`id`, `adjective`) VALUES (?, ?)");
            preparedStatement.setInt(1, generateIdAdjective());
            preparedStatement.setString(2, adjective);
            preparedStatement.executeUpdate();
            conn.close();
        }catch (SQLException e){
            System.out.println("Can't save adjective, problem with database");
        }
    }

    public List<String> nounsFromDatabase() {
        List<String> nounList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
            PreparedStatement statement = conn.prepareStatement("SELECT noun FROM noun");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                nounList.add(rs.getString(1));
            }
        }catch (SQLException e){
            System.out.println("Can't create noun list, problem with database. \n" +
                    "Just nouns from text document.");
        }

                return nounList;
    }

    public List<String> adjectivesFromDatabase() {
        List<String> adjectiveList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);

            PreparedStatement statement = conn.prepareStatement("SELECT adjective FROM adjective");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                adjectiveList.add(rs.getString(1));
            }
        }catch (SQLException e){
            System.out.println("Can't create adjective list, problem with database. \n" +
                    "Just adjective from text document.");
        }

                return adjectiveList;
    }

    private int generateIdNoun(){
        int lastIdFromDatabase = 0;
        try {
            Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(ID) FROM noun");
            ResultSet rs = statement.executeQuery();
            rs.next();
             lastIdFromDatabase = rs.getInt(1);
        }catch (SQLException e){
            System.out.println("Can't get last id, problem with database");
        }
                return lastIdFromDatabase + 1;

    }

    private int generateIdAdjective()  {
        int lastIdFromDatabase = 0;
        try {
                Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
                PreparedStatement statement = conn.prepareStatement("SELECT MAX(ID) FROM adjective");
                ResultSet rs = statement.executeQuery();
                rs.next();
                 lastIdFromDatabase = rs.getInt(1);
        }catch (SQLException e){
            System.out.println("Can't get last id, problem with database");
        }
                return lastIdFromDatabase + 1;

    }

    private int generateIdBand() {
        int lastIdFromDatabase = 0;
        try {
                Connection conn = DriverManager.getConnection(URL + TIME_ZONE, USER, PASS);
                PreparedStatement statement = conn.prepareStatement("SELECT MAX(ID) FROM band");
                ResultSet rs = statement.executeQuery();
                rs.next();
                 lastIdFromDatabase = rs.getInt(1);
        }catch (SQLException e){
            System.out.println("Can't get last id, problem with database");
        }
                return lastIdFromDatabase + 1;
    }


}
