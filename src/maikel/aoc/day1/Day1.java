package maikel.aoc.day1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Day1 {
    private static final String absFilePath = "C:\\Projects\\AdventOfCode\\Resources\\1\\input.txt";
    private static final int sumValue = 2020;

    private static int getHalfOfSumValue(){
        return sumValue / 2;
    }

    public static void main(String[] args) {
        ArrayList<Integer> bigNums = new ArrayList();
        ArrayList<Integer> smallNums = new ArrayList();

        //Handle Input
        try (Stream<String> stream = Files.lines(Paths.get(absFilePath)))
        {
            stream.forEach((s) -> {
                int lineValue = Integer.parseInt(s);

                if(lineValue < getHalfOfSumValue()){
                    smallNums.add(lineValue);
                }
                else{
                    bigNums.add(lineValue);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Part 1
        for(int i=0;i<bigNums.size();i++){
            for(int j=0;j<smallNums.size();j++){
                int bigVal = bigNums.get(i);
                int smallVal = smallNums.get(j);

                if(bigVal+smallVal == sumValue){
                    int sumMultiplication = bigVal*smallVal;
                    System.out.println(bigVal + " x " + smallVal + " = " + sumMultiplication);
                }
            }
        }

        //Part 2
        for(int x=0;x<bigNums.size();x++){
            for(int y=0;y<smallNums.size();y++){
                for(int z=0;z<smallNums.size();z++){
                    int bigVal = bigNums.get(x);
                    int smallVal1 = smallNums.get(y);
                    int smallVal2 = smallNums.get(z);

                    if(bigVal + smallVal1 + smallVal2 == sumValue){
                        int sumMultiplication = bigVal * smallVal1 * smallVal2;
                        System.out.println(bigVal + " x " + smallVal1 + " x " + smallVal2 + " = " + sumMultiplication);
                    }
                }
            }
        }
    }
}
