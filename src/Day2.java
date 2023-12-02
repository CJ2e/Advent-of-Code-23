import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Day2 {

    static final int BLUE_MAX = 14;
    static final int RED_MAX = 12;
    static final int GREEN_MAX = 13;
    public static void main(String[] args) {
        ArrayList<Integer> validGameIDS = new ArrayList<>();

        File file = new File("inputDay2.txt");
        int gameID = 1;
        int powerSum = 0;
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String str = in.nextLine();
                String[] groups = str.strip().split(": ")[1].split("; ");
                Map<String, Integer> cubeData = new HashMap<>();
                cubeData.put("red", 0);
                cubeData.put("green", 0);
                cubeData.put("blue", 0);
                boolean impossibleGame = false;
                int power;

                for (String group : groups) {
                    for (String elem : group.split(", ")) {
                        String[] colourVal = elem.split(" ");
                        if (Integer.parseInt(colourVal[0]) > cubeData.get(colourVal[1])) {
                            cubeData.put(colourVal[1], Integer.parseInt(colourVal[0]));
                        }
                    }
                    if (cubeData.get("red") > RED_MAX || cubeData.get("green") > GREEN_MAX
                    || cubeData.get("blue") > BLUE_MAX) {
                        impossibleGame = true;
                    }
                }
                if (!impossibleGame) {
                    validGameIDS.add(gameID);
                }
                power = cubeData.get("red") * cubeData.get("green") * cubeData.get("blue");
                gameID++;
                powerSum += power;

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        int sum = 0;
        for (Integer id : validGameIDS) {
            sum += id;
        }
        System.out.println(sum);
        System.out.println(powerSum);
    }
}
