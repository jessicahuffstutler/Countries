import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {

    static Country country;

    public static void main(String[] args) {
        HashMap<String, ArrayList<Country>> countries = new HashMap(); //where the key is the first letter of the country name.
        String fileContent = readFile("countries.txt"); //file is String
        String[] countriesContent = fileContent.split("\n"); //turn file into String[]?

        for (String countryName : countriesContent) {
            String firstLetter = String.valueOf(countryName.charAt(0));
            ArrayList<Country> list = countries.get(firstLetter);
            String[] column = countryName.split("\\|");
            String abbrev = column[0];
            String name = column[1];
            Country country = new Country(name, abbrev);
            if (list == null) {
                list = new ArrayList();
                list.add(country);
                countries.put(firstLetter, list);
            } else {
                list.add(country);
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type a letter to look up a Country name.");
        String letter = scanner.nextLine().toUpperCase();
        ArrayList<Country> letterList = countries.get(letter);
        String newFileName = String.format("%s_countries.txt", letter);

        if(countries.containsKey(letter)) {
            String contents = "";
            for (Country names : letterList) {
                contents = contents + String.format("%s %s\n", names.abbrev, names.name);
                writeFile(newFileName, contents);
            }
        }

        saveCountry();
    }

    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }

    static void writeFile(String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {

        }
    }

    static void saveCountry() {
        File f = new File("save.json"); //json library wants you to set getters for anything you want to save
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.serialize(country);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {
            System.out.println("Save not successful.");
        }
    }

    //Save an "X_countries.txt" file, where X is the letter they typed, which only lists the countries starting with that letter
}
