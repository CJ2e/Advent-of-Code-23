import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day3 {
    private static int partSum;
    private static int gearRatioSum;
    private static void getSymIndex(int lineNum, Map<Integer, String> grid, ArrayList<Symbols> symbols) {
        int j = 0;
        for (String ch : grid.get(lineNum).split("")) {
            if (!Character.isDigit(ch.charAt(0)) && !ch.equals(".")) {
                Symbols symbol = new Symbols(ch, lineNum, j);
                symbols.add(symbol);
            }
            j++;
        }
    }
    private static void getPartNums(Map<Integer, String> grid, ArrayList<PartNum> allParts) {
        for (int i = 1; i < grid.size() + 1; i++) {
            String str = grid.get(i);
            StringBuilder num = new StringBuilder();
            int start = -1;
            int j = 0;
            for (String ch : str.split("")){
                if (Character.isDigit(str.charAt(j)) && num.isEmpty()) {
                    num.append(ch);
                    start = j;
                } else if (Character.isDigit(str.charAt(j))) {
                    num.append(ch);
                } else if (!num.isEmpty() && !Character.isDigit(str.charAt(j))){
                    int val = Integer.parseInt(num.toString());
                    PartNum part = new PartNum(i, start, (j - 1), val);
                    num.setLength(0);
                    allParts.add(part);
                }
                if (!num.isEmpty() && Character.isDigit(str.charAt(j)) && j == str.length() - 1) {
                    int val = Integer.parseInt(num.toString());
                    PartNum part = new PartNum(i, start, (j - 1), val);
                    num.setLength(0);
                    allParts.add(part);
                }
                j++;
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay3.txt");

        //Get number of lines in file
        long lines = 0;
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {
            while (lnr.readLine() != null) {
                lines = lnr.getLineNumber();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(file);
        Map<Integer, String> grid = new HashMap<>();
        ArrayList<Symbols> symbols = new ArrayList<>();
        ArrayList<PartNum> allParts = new ArrayList<>();
        ArrayList<Integer> validParts = new ArrayList<>();

        for (int i = 1; i < lines + 1; i++) {
           grid.put(i, in.nextLine());
        }
//        for (int i = 1; i < grid.size() + 1; i++) {
//            System.out.println(grid.get(i));
//        }
        for (int i = 1; i < grid.size() + 1; i++) {
            getSymIndex(i, grid, symbols);
        }
        getPartNums(grid, allParts);
//        for (PartNum part : allParts) {
//            System.out.println(part);
//        }
        for (PartNum part : allParts) {
            boolean valid = false;
            for (Symbols sym : symbols) {
                for (int i = -1; i < 2 && !valid; i++) {
                    if (sym.getRow() == part.getRow() + i) {
                        for (int j = part.getStart() - 1; j < part.getEnd() + 2; j++) {
                            if (sym.getIndex() == j) {
                                valid = true;
                                if (sym.getVal().equals("*")) {
                                    sym.addCount();
                                    sym.addGearVal(part.getVal());
                                }
                            }
                        }
                    }
                }
            }
            if (valid) {
                validParts.add(part.getVal());
            }
        }
        for (Symbols sym : symbols) {
            if (sym.getVal().equals("*") && sym.getCount() == 2) {
                gearRatioSum += sym.getGearRatio();
            }
        }
        for (Integer i : validParts) {
            partSum += i;
        }
        System.out.println(partSum);
        System.out.println(gearRatioSum);

    }

}
