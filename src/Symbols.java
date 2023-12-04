import java.util.ArrayList;

public class Symbols {
    private final int index;
    private final int row;
    private final String val;

    public int getCount() {
        return count;
    }

    private int count;
    private final ArrayList<Integer> gearVal = new ArrayList<>();

    public Symbols(String val, int row, int index) {
        this.row = row;
        this.index = index;
        this.val = val;
        this.count = 0;
    }
    @Override
    public String toString() {
        return (row + "," + index);
    }
    public int getRow() {
        return this.row;
    }
    public int getIndex() {
        return index;
    }
    public String getVal() {
        return val;
    }
    public void addCount() {
        if (this.val.equals("*")) {
            count++;
        } else {
            count = 0;
        }
    }
    public void addGearVal(int partNum) {
        if (this.val.equals("*")) {
            gearVal.add(partNum);
        }
    }
    public int getGearRatio() {
        int gearRatio;
        if (this.count == 2) {
            gearRatio = gearVal.get(0) * gearVal.get(1);
        } else {
            gearRatio = 0;
        }
        return gearRatio;
    }
}
