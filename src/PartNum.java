public class PartNum {
    private final int startIndex;
    private final int endIndex;
    private final int val;
    private final int row;

    public PartNum(int row, int start, int end, int val) {
        this.row = row;
        this.startIndex = start;
        this.endIndex = end;
        this.val = val;
    }
    @Override
    public String toString() {
        return ("Row: " + this.row + " | Value: " + this.val + " | Start: " + this.startIndex + " | End: " + this.endIndex);
    }
    public int getRow() {
        return this.row;
    }
    public int getStart() {
        return this.startIndex;
    }
    public int getEnd() {
        return this.endIndex;
    }
    public int getVal() {
        return this.val;
    }
}
