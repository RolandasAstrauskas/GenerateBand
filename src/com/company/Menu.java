package com.company;

import com.company.band.Band;
import com.company.wordReader.WordList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {

    static int RESPONSE_FROM_USER_IN_MENU = 0;
    static int RESPONSE_FROM_USER_CREATE_REBUILD = 0;

    public void mainMenu() throws IOException, SQLException {

        createMenuForUser();
    }

     private static void createMenuForUser() throws IOException, SQLException {

        int response = createMenu();

        switch (response){
            case 1:
                createBand();
                break;
            case 2:
                insertNoun();
                break;
            case 3:
                insertAdjective();
                break;
            case 4:
                checkAllYourBands();
                break;
            case 5:
                System.out.println("Exit");
                break;
            default:
        }
    }

    private static void checkAllYourBands() throws SQLException, IOException {
        DatabaseConnection connection = new DatabaseConnection();

        List<Band> bandList = connection.bandList();
        System.out.println(bandList);

        createMenuForUser();
    }

    private static void insertAdjective() throws SQLException, IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("You can always add new adjective to create new crazy bands name! \n" +
                "Type it here:");
        String newAdjectiveFromUser = scanner.nextLine();
        connection.saveAdjective(newAdjectiveFromUser);

        createMenuForUser();
    }

    private static void insertNoun() throws SQLException, IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("You can always add new noun to create new crazy bands name! \n" +
                "Type it here:");
        String newNounFromUser = scanner.nextLine();
        connection.saveNoun(newNounFromUser);

        createMenuForUser();
    }

    private static void createBand() throws IOException, SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);
        WordList wordList = new WordList();

        List<String> adjectiveList =  Stream.concat(connection.adjectivesFromDatabase().stream(),
                wordList.readerAdjective().stream())
                .collect(Collectors.toList());

        List<String> nounList = Stream.concat(connection.nounsFromDatabase().stream(),
                wordList.readerNoun().stream())
                .collect(Collectors.toList());


        System.out.println("Choose band name adjective from list and type it \n" +
                adjectiveList.toString());
        String bandAdjectiveFromUser = scanner.nextLine();

        while(!checkAdjectiveInsertItIsFromTheList(bandAdjectiveFromUser, adjectiveList)){
            System.out.println("Insert adjective from the list");
             bandAdjectiveFromUser = scanner.nextLine();
        }

        System.out.println("Choose band name noun from list and type it \n" +
                nounList.toString());
        String bandNounFromUser = scanner.nextLine();

        while(!checkNounInsertItIsFromTheList(bandNounFromUser, nounList)){
            System.out.println("Insert noun from the list");
            bandNounFromUser = scanner.nextLine();
        }

        System.out.println("Let's fill your band with new members!!");

        System.out.println("Your new drummer is?!?");
        String drummerFromUser = scanner.nextLine();

        System.out.println("Your new vocalist is?!?");
        String vocalistFromUser = scanner.nextLine();

        System.out.println("Your new bass guitarist is?!?");
        String bassGuitaristFromUser = scanner.nextLine();

        System.out.println("Your new electric guitarist is?!?");
        String electricGuitaristFromUser = scanner.nextLine();

        createNewBandFromUserInsert(bandAdjectiveFromUser,
                bandNounFromUser,
                drummerFromUser,
                vocalistFromUser,
                bassGuitaristFromUser,
                electricGuitaristFromUser);
    }

    private static boolean checkNounInsertItIsFromTheList(String bandNounFromUser, List<String> nounList) {
        boolean isItFromList = nounList.stream()
                .anyMatch(s -> s.contains(bandNounFromUser));
        return isItFromList;
    }

    private static boolean checkAdjectiveInsertItIsFromTheList(String bandAdjectiveFromUser, List<String> adjectiveList) {
        boolean isItFromList = adjectiveList.stream()
                .anyMatch(s -> s.contains(bandAdjectiveFromUser));
        return isItFromList;
    }

    private static void createNewBandFromUserInsert(String bandAdjectiveFromUser,
                                                    String bandNounFromUser,
                                                    String drummerFromUser,
                                                    String vocalistFromUser,
                                                    String bassGuitaristFromUser,
                                                    String electricGuitaristFromUser) throws IOException, SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);

        Band band = new Band(bandAdjectiveFromUser,
                            bandNounFromUser,
                            drummerFromUser,
                            vocalistFromUser,
                            bassGuitaristFromUser,
                            electricGuitaristFromUser
        );

        System.out.println("Your new band!!! \n" + band + "\n" +
                            " You want to save it press  - 1 if you want to rebuild press - 2?\n" +
                            " Just back to main menu press - 3");

        try {
            RESPONSE_FROM_USER_CREATE_REBUILD = scanner.nextInt();
        }catch(InputMismatchException a){
            System.out.println("Wrong insert try again!");
            createNewBandFromUserInsert(bandAdjectiveFromUser,
                                        bandNounFromUser,
                                        drummerFromUser,
                                        vocalistFromUser,
                                        bassGuitaristFromUser,
                                        electricGuitaristFromUser);


        }
        checkInsertedNumberFromUserAndSave(bandAdjectiveFromUser,
                                    bandNounFromUser,
                                    drummerFromUser,
                                    vocalistFromUser,
                                    bassGuitaristFromUser,
                                    electricGuitaristFromUser,

                                    connection,
                                    scanner,
                RESPONSE_FROM_USER_CREATE_REBUILD);
    }

    private static void checkInsertedNumberFromUserAndSave(String bandAdjectiveFromUser,
                                                    String bandNounFromUser,
                                                    String drummerFromUser,
                                                    String vocalistFromUser,
                                                    String bassGuitaristFromUser,
                                                    String electricGuitaristFromUser,
                                                    DatabaseConnection connection,
                                                    Scanner scanner,
                                                    int answerFromUser) throws SQLException, IOException {
        while (!(answerFromUser == 1) && !(answerFromUser == 2) && !(answerFromUser == 3)) {
            System.out.println("You need insert  1 for save band or 2 for rebuild!");
            answerFromUser = scanner.nextInt();
        }
                    if (answerFromUser == 1) {
                        connection.saveBand(bandAdjectiveFromUser,
                                bandNounFromUser,
                                drummerFromUser,
                                vocalistFromUser,
                                bassGuitaristFromUser,
                                electricGuitaristFromUser);
                        createMenuForUser();
                    }else if(answerFromUser == 2){
                        createBand();
                    }else{
                        createMenuForUser();
                    }
    }

    private static int createMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create your amazing band!!!");
        System.out.println();
        System.out.println( "Create band press - 1 \n" +
                            "Insert new noun press - 2 \n" +
                            "Insert new adjective press - 3 \n" +
                            "Check your bands - 4 \n" +
                            "Exit program press - 5");

        try {
            RESPONSE_FROM_USER_IN_MENU = scanner.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Need insert number from menu!");
            RESPONSE_FROM_USER_IN_MENU = createMenu();
        }
        
        checkResponseFromUser(RESPONSE_FROM_USER_IN_MENU);

        return RESPONSE_FROM_USER_IN_MENU;
    }

    private static void checkResponseFromUser(int response) {
        if(!(response > 0 && response < 6)){
            System.out.println("Insert value from menu must be from 1 to 5!");
            createMenu();
        }
    }
}
