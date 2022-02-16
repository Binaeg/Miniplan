package Miniplan_2_0.POJO;

import java.util.Calendar;
import java.util.List;

public class MiniBuilder {
    private String name;
    private int experience;
    private List<Weekday> availableDays;
    private Calendar birthdate;

    public MiniBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MiniBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public MiniBuilder setAvailableDays(List<Weekday> availableDays) {
        this.availableDays = availableDays;
        return this;
    }

    public MiniBuilder setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public Mini build() {
        return new Mini(name, experience, availableDays, birthdate);
    }
}
