package Miniplan_2_0.POJO;

public class TerminBuilder {
    private String date;
    private Weekday weekday;
    private String time;
    private int amountOfMinis;

    public TerminBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public TerminBuilder setWeekday(Weekday weekday) {
        this.weekday = weekday;
        return this;
    }

    public TerminBuilder setTime(String time) {
        this.time = time;
        return this;
    }

    public TerminBuilder setAmountOfMinis(int amountOfMinis) {
        this.amountOfMinis = amountOfMinis;
        return this;
    }

    public Termin build() {
        return new Termin(date, weekday, time, amountOfMinis);
    }
}
