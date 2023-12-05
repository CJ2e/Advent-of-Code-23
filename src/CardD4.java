import java.util.ArrayList;
import java.util.Objects;

public class CardD4 {
    private final int ID;
    private final ArrayList<Integer> winNums = new ArrayList<>();
    private final ArrayList<Integer> myNums = new ArrayList<>();
    private int count = 0;
    private int copies = 0;
    public CardD4(int id, String winners, String myNums) {
        this.ID = id;
        for (String num : winners.split(" ")) {
            boolean numFound = false;
            for (Integer val : winNums) {
                if (!num.isEmpty() && Integer.parseInt(num) == val) {
                    numFound = true;
                    break;
                }
            }
            if (!numFound && !num.isEmpty()) {
                winNums.add(Integer.parseInt(num));
            }
        }
        for (String num : myNums.split(" ")) {
            if (!num.isEmpty()) {
                this.myNums.add(Integer.parseInt(num));
            }
        }
    }
    public String toString() {
        return ("Card " + ID + " | Worth: " + getWorth());
    }
    public double getWorth() {
        double worth = 0;
        this.count = 0;
        for (Integer num : myNums) {
            for (Integer winner : winNums) {
                if (Objects.equals(num, winner)) {
                    this.count++;
                }
            }
        }
        if (this.count > 0) {
            worth = Math.pow(2, this.count - 1);
        }
        return worth;
    }
    public int getCount() {
        return count;
    }
    public int getID() {
        return this.ID;
    }
    public void addCopy() {
        this.copies++;
    }
    public int getCopies() {
        return this.copies;
    }
}
