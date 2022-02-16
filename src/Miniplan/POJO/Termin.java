package Miniplan.POJO;

import java.util.ArrayList;
import java.util.List;

public class Termin {
    private String date;
    private Weekday weekday;
    private String time;
    private int amountOfMinis;
    private List<Mini> deployedMinis;

    public Termin(String date, Weekday weekday, String time, int amountOfMinis) {
        this.date = date;
        this.weekday = weekday;
        this.time = time;
        this.amountOfMinis = amountOfMinis;
        deployedMinis = new ArrayList<>();
    }

    public List<Mini> getDeployedMinis() {
        return deployedMinis;
    }

    public String getDate() {
        return date;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public String getTime() {
        return time;
    }

    public int getAmountOfMinis() {
        return amountOfMinis;
    }

    public String deployedMinisToString() {
        StringBuilder result = new StringBuilder();
        for (Mini mini : deployedMinis) {
            result.append(mini.toString()).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }
}
