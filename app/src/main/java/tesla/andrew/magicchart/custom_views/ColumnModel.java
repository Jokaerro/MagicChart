package tesla.andrew.magicchart.custom_views;

/**
 * Created by TESLA on 27.07.2017.
 */

public class ColumnModel {
    private int learnedCount;
    private int repeatCount;
    private int newCount;
    private String day;
    private String month;

    public ColumnModel(int learnedCount, int repeatCount, int newCount, String day, String month) {
        this.learnedCount = learnedCount;
        this.repeatCount = repeatCount;
        this.newCount = newCount;
        this.day = day;
        this.month = month;
    }

    public int getLearnedCount() {
        return learnedCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public int getNewCount() {
        return newCount;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }
}
