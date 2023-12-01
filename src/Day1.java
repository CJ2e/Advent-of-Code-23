import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static int charToInt(ArrayList<Character> charList) {
        char firstVal = charList.get(0);
        char lastVal = charList.get(charList.size() - 1);
        String val = String.valueOf(firstVal) + lastVal;
        return Integer.parseInt(val);
    }

    public static int getCalibrationVal(String data) {
        ArrayList<Character> intChars = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (Character.isDigit(ch)) {
                intChars.add(ch);
            }
        }
        int calibrationVal = charToInt(intChars);
        intChars.clear();
        return calibrationVal;
    }

    public static void main(String[] args) {
        ArrayList<Integer> calibrationValues = new ArrayList<>();
        try {
            File file = new File("inputDay1.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                int val = getCalibrationVal(data);
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
