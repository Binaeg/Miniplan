package Miniplan_2_0.POJO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Mini {
    private final String name;
    private final int experience;
    private final List<Weekday> availableDays;
    private final List<Mini> partners;
    private int amountOfEntries = 0;
    private final Calendar birthdate;

    public Mini(String name, int experience, List<Weekday> availableDays, Calendar birthdate) {
        this.name = name;
        this.experience = experience;
        this.availableDays = availableDays;
        this.partners = new ArrayList<>();
        this.birthdate = birthdate;
    }

    public int getAmountOfEntries() {
        return amountOfEntries;
    }

    public Mini incrementAmountOfEntries() {
        this.amountOfEntries++;
        return this;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public List<Mini> getPartners() {
        return partners;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public List<Weekday> getAvailableDays() {
        return availableDays;
    }

    public String toString() {
        return name.split(" ")[0] + " " + name.split(" ")[1].charAt(0) + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mini mini = (Mini) o;
        return experience == mini.experience && amountOfEntries == mini.amountOfEntries && Objects.equals(name, mini.name) && Objects.equals(availableDays, mini.availableDays) && Objects.equals(partners, mini.partners) && Objects.equals(birthdate, mini.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, availableDays, partners, amountOfEntries, birthdate);
    }
}
