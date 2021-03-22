package maikel.aoc.day2;

public class PasswordInput {
    public int minCount;
    public int maxCount;
    public char letterToContain;
    public String password;

    public PasswordInput(int minCount, int maxCount, char letterToContain, String password){
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.letterToContain = letterToContain;
        this.password = password;
    }

    @Override
    public String toString(){
        return minCount + " " + maxCount + " "  + letterToContain + " " + password;
    }
}
