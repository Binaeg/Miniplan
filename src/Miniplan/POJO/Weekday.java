package Miniplan.POJO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public enum Weekday {
    THURSDAY("Donnerstag"), FRIDAY("Freitag"), SUNDAY("Sonntag");

    private String name;

    Weekday(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Weekday getWeekdayFromInt(int i) {
        switch (i) {
            case 1:
                return Weekday.SUNDAY;
            case 5:
                return Weekday.THURSDAY;
            case 6:
                return Weekday.FRIDAY;
            default:
                return null;
        }
    }

    public static Weekday getWeekdayFromDate(String date){
        DateFormat sourceFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sourceFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Weekday.getWeekdayFromInt(c.get(Calendar.DAY_OF_WEEK));
    }
}
