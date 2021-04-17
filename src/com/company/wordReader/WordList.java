package com.company.wordReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {

    final private String NOUN_PATH = "src/properties/noun";
    final private String ADJECTIVE_PATH = "src/properties/adjective";


    public List<String> readerNoun() throws IOException {

        Scanner s = new Scanner(new File(NOUN_PATH));
        List<String> list = new ArrayList<>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        return list;
    }

    public List<String> readerAdjective() throws IOException {

        Scanner s = new Scanner(new File(ADJECTIVE_PATH));
        List<String> list = new ArrayList<>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        return list;
    }
}


