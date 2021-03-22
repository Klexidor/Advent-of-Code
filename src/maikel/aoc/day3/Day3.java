package maikel.aoc.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day3 {
    private static final String absFilePath = "C:\\Projects\\AdventOfCode\\Resources\\3\\input.txt";

    private static int mapWidth;
    private static int mapHeight;
    private static char[][] map;

    private static int treesEncountered = 0;

    private int hStepCount = 3;
    private int vStepCount = 1;

    private static final Slope[] slopes = {
            new Slope(1,1),
            new Slope(3,1),
            new Slope(5,1),
            new Slope(7,1),
            new Slope(1,2),
    };


    private static String[] stringMap;

    public static void main(String[] args){
        try
        {
            Stream<String> stream = Files.lines(Paths.get(absFilePath));

            stringMap = stream.toArray(String[]::new);

            mapWidth = stringMap[0].length();
            mapHeight = stringMap.length;

        } catch (IOException e) {
            e.printStackTrace();
        }

        map = new char[mapHeight][mapWidth];

        for(int y=0;y<mapHeight;y++) {
            for (int x = 0; x < mapWidth; x++) {
                map[y][x] = stringMap[y].charAt(x);
            }
        }

        for(Slope s : slopes){
            traverseMap(s);
        }

        //printMap(map);


    }

    private static void traverseMap(Slope slope){
        int posX = 0;
        int posY = 0;

        int treesEncountered = 0;

        while(posY < mapHeight - 1){
            posX = updatePosX(posX, slope.slopeX);
            posY += slope.slopeY;

            if(encounteredTree(posX, posY)){
                treesEncountered++;
            }
        }

        System.out.println("With Slope " + slope.slopeX + " " + slope.slopeY + " we encountered " + treesEncountered + " trees.");
    }

    private static int updatePosX(int curPosX, int slopeX){
        int newPosX = curPosX + slopeX;

        if(newPosX > mapWidth - 1){
            newPosX = newPosX - mapWidth;
        }

        return newPosX;
    }

    private static Boolean encounteredTree(int posX, int posY){
        Boolean result = map[posY][posX] == '#' ? true : false;

        return result;
    }

    private static void printMap(char[][] map){
        StringBuilder sb = new StringBuilder();

        for(int y=0;y<mapHeight;y++) {
            for (int x = 0; x < mapWidth; x++) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
