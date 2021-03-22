package maikel.aoc.day2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Day2 {
    private static final String absFilePath = "C:\\Projects\\AdventOfCode\\Resources\\2\\input.txt";

    public static void main(String[] args){
        ArrayList<PasswordInput> passwords = new ArrayList();
        ArrayList<PasswordInput> validPasswords = new ArrayList();
        ArrayList<PasswordInput> validPasswords2 = new ArrayList();

        //Handle Input
        try (Stream<String> stream = Files.lines(Paths.get(absFilePath)))
        {
            stream.forEach((s) -> {
                int i = 0;

                int min = -1;
                int max = -1;
                char letter = '?';
                String passwordString = "";

                //Parse min
                if(s.charAt(1) == '-'){
                    i = 1;
                }else{
                    i = 2;
                }

                min = Integer.parseInt(s.substring(0,i));

                i = s.indexOf('-') + 1;

                //Parse max
                if(s.charAt(i + 1) == ' '){
                    max = Integer.parseInt(s.substring(i,i+1));

                    i = i+1;
                }else{
                    max = Integer.parseInt(s.substring(i,i+2));

                    i = i+2;
                }

                //Parse letter
                letter = s.charAt(i+1);

                i = i+4;

                //Parse password
                passwordString = s.substring(i);

                PasswordInput password = new PasswordInput(min, max, letter, passwordString);
                passwords.add(password);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Part 1
        for(PasswordInput p : passwords){
            if(isPasswordValidForPart1(p)){
                validPasswords.add(p);
            }
        }

        //Part 2
        for(PasswordInput p : passwords){
            if(isPasswordValidForPart1(p)){
                validPasswords.add(p);
            }
            if(isPasswordValidForPart2(p)){
                validPasswords2.add(p);
            }
        }

        System.out.println("Amount of valid passwords for part 1: " + validPasswords.size());
        System.out.println("Amount of valid passwords for part 2: " + validPasswords2.size());
    }

    private static Boolean isPasswordValidForPart1(PasswordInput password){
        int count = 0;
        char[] passwordChars = password.password.toCharArray();

        for(char c : passwordChars){
            if(c == password.letterToContain){
                count++;
            }
        }

        if(count >= password.minCount && count <= password.maxCount){
            return true;
        }

        return false;
    }

    private static Boolean isPasswordValidForPart2(PasswordInput password){
        int count = 0;
        Boolean char1Correct = false;
        Boolean char2Correct = false;

        char[] passwordChars = password.password.toCharArray();

        if(passwordChars[password.minCount-1] == password.letterToContain){
            char1Correct = true;
        }

        if(passwordChars[password.maxCount-1] == password.letterToContain){
            char2Correct = true;
        }

        if(char1Correct != char2Correct){
            return true;
        }

        return false;
    }
}
