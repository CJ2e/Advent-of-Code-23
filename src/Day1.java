import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static int getCalVal(String data) {
        int retVal;
        String firstVal = "";
        String lastVal = "";

        String[] numList = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine",
        };
        int[][] numIndexes = new int[10][100];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < data.length();) {
                int pos = data.indexOf(numList[i]);
                if (pos == -1) {
                    numIndexes[i][j] = pos;
                    j++;
                }
                while (pos != -1) {
                    numIndexes[i][j] = pos;
                    j++;
                    pos = data.indexOf(numList[i], pos + 1);
                }
                pos = data.indexOf(numList[i + 10]);
                if (pos == -1) {
                    numIndexes[i][j] = pos;
                    j++;
                }
                while (pos != -1) {
                    numIndexes[i][j] = pos;
                    j++;
                    data = data.substring(0, pos + 1) + numList[i] + data.substring(pos + 2);
                    System.out.println(data);
                    pos = data.indexOf(numList[(i + 10)], pos + 1);
                }
                numIndexes[i][j] = -2;
            }
        }
        int firstValIndex = -1;
        int lastValIndex = -1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; numIndexes[i][j] != -2; j++) {
                if (numIndexes[i][j] >= 0 && firstVal.isEmpty()) {
                    firstVal = numList[i];
                    firstValIndex = numIndexes[i][j];
                    lastVal = numList[i];
                    lastValIndex = numIndexes[i][j];
                } else if (numIndexes[i][j] >= 0) {
                    if (numIndexes[i][j] < firstValIndex) {
                        firstVal = numList[i];
                        firstValIndex = numIndexes[i][j];
                    } else if (numIndexes[i][j] > lastValIndex) {
                        lastVal = numList[i];
                        lastValIndex = numIndexes[i][j];
                    }
                }
            }
        }
        retVal = Integer.parseInt(firstVal + lastVal);
        return retVal;
    }
    public static void main(String[] args) {
        ArrayList<Integer> calibrationValues = new ArrayList<>();
        try {
            File file = new File("inputDay1.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                int val = getCalVal(data);
                calibrationValues.add(val);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
        int sum = 0;
        for (int val : calibrationValues) {
            sum += val;
            //System.out.println(val);
        }
        System.out.println("Sum of calibration values: " + sum);
    }
}
