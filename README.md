# GenerateBand
Java 11, JDBC and MySQL


Application uses  - JDBC library, Java 11 and MySQL.

Application purposes - generate band name from given adjectives and nouns,
and create band members(vocalist, drummer, electric guitarist and bass guitarist, names are entered by the user).
Additional functionality - save nouns and adjectives to database and use them to make bands name.

Application has 5 class - Band, WordList, DatabaseConnection, Main and Menu.
Each of them has its own purpose.

***
Band - represent "Band" object with its name and four band members.

***
WordList - scans files with nouns and adjectives and adds them to the lists.

***
DatabaseConnection - use JDBC library to connect with database which is local. Use "Band" object to save it to database.
Also saves nouns and adjectives inserted from user. Retrieves band, nouns and adjectives data from database.
"DatabaseConnection" has all method which are related with database for an application use.
If band, nouns or adjective can't be saved or retrieved from database console display message, for example:
"Can't save band, problem with database",
"Can't create band list, problem with database",
"Can't get last id, problem with database" and so on.

***
Menu - create simple menu for user. Numbers(1,2,3,4,5) helps  users to navigate and to choose which features they want.
Also checks data from user and validate it.

MENU
Create band press - 1: band name should use nouns and adjective from the list if you user want different noun or adjective he must first insert words to the list(save to database).
Insert new noun press - 2: user can add new noun and later use it(save to database).
Insert new adjective press - 3: user can add new adjective and later use it(save to database).
Check your bands - 4: user can check his created bands names and members(retrieved from database).
Exit program press - 5: stops application

***
Main - run the application.
