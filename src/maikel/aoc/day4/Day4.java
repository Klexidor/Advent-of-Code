package maikel.aoc.day4;

import maikel.aoc.day2.PasswordInput;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day4 {

    private static final String absFilePath = "C:\\Projects\\AdventOfCode\\Resources\\4\\input.txt";
    private static int validPassportsCount;

    private static ArrayList<String> passportData;

    public static void main(String[] args){
        passportData = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(absFilePath)))
        {
            stream.forEach((s) -> {
                //Gather tokens until we get an empty one. Gather these in a list.
                String[] tokens = s.split(" ");

                if((tokens.length != 0 && tokens[0].isEmpty())){
                    //Check the validity here.
                    Boolean isValid = true;

                    //if contains 7 fields, only CID might be missing, which is valid. Otherwise it's automatically invalid.
                    if(passportData.size() == 7){
                        for (String t : passportData){
                            if(t.contains("cid")){
                                System.out.println("INVALID: Has 7 fields and contains CID");
                                //is invalid, break loop
                                isValid = false;
                                break;
                            }
                        }
                    }
                    else if(passportData.size() < 7){
                        System.out.println("INVALID: Has less than 7 fields");
                        isValid = false;
                    }

                    if(isValid){

                        if(IsPassportDataSane(passportData)){
                            validPassportsCount++;
                            System.out.println("valid count: " + validPassportsCount);
                        }
                    }

                    passportData.clear();
                } else {
                    for(String token : tokens){
                        passportData.add(token);
                    }
                }

                for(String t : tokens){
                    System.out.println(t);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Valid Passports: " + validPassportsCount);
    }

    private static Boolean IsPassportDataSane(ArrayList<String> passportData){
        HashMap<String, String> passportMap = new HashMap<>();

        for(String s : passportData){
            String[] kvp = s.split(":");
            passportMap.put(kvp[0], kvp[1]);
        }

        for(String k : passportMap.keySet()){
            switch (k){
                case "byr":
                    if(!isBirthdayValid(passportMap.get(k)))
                        return false;
                case "iyr":
                    break;
                case "eyr":
                    break;
                case "hgt":
                    break;
                case "hcl":
                    break;
                case "ecl":
                    break;
                case "pid":
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    private static boolean isBirthdayValid(String birthdate){
        if(birthdate.length() != 4){
            return false;
        }

        int birthDayNumber = Integer.parseInt(birthdate);

        if(birthDayNumber < 1920 || birthDayNumber > 2002)
            return false;

        return true;
    }
}
