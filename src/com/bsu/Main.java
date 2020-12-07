package com.bsu;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    final private static String GUITAR_DATABASE_FILENAME = "data/database.csv";

    public static void main(String[] args) {
        List<Guitar> guitarList = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(GUITAR_DATABASE_FILENAME))) {
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(";");
                guitarList.add(Guitar.read(line));
            }
        } catch (Exception ex) {
            System.err.println("Error in reading from \"" + GUITAR_DATABASE_FILENAME + "\"");
        }

        ///get all the guitars that were manufactured by "adams"(filter)
        guitarList.stream().filter(o -> o.getManufacturer().equalsIgnoreCase("adams")).forEach(System.out::println);

        ///find biggest price(map, reduce)
        int maxPrice = guitarList.stream().map(Guitar::getCost).reduce(Integer::max).orElse(-1);
        System.out.println("maxPrice = " + maxPrice);

        ///map of manufacturers and models(toMap)
        Map<String, String> map = guitarList.stream().collect(Collectors.toMap(Guitar::getModel,Guitar::getManufacturer));
        System.out.println(map);

        ///find first in stream(findFirst)
        System.out.println(guitarList.stream().findFirst().orElse(null));

        ///find any in stream(findAny)
        System.out.println(guitarList.stream().findAny().orElse(null));

        ///tries to find fender(anyMatch)
        if(guitarList.stream().anyMatch(o->o.getManufacturer().equals("fender"))){
            System.out.println("fender found!");
        }
        else{
            System.out.println("fender not found!");
        }

        ///group guitars by manufacturer(groupingBy)
        System.out.println(guitarList.stream().collect(Collectors.groupingBy(Guitar::getManufacturer)));


    }
}
