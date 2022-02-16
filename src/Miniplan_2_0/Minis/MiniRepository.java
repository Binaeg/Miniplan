package Miniplan_2_0.Minis;

import Miniplan_2_0.POJO.*;

import java.io.IOException;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class MiniRepository implements IMiniRepository {
    private static String MINIS_PATH = "./Minis.csv";

    private final int NAME_COLUMN = 0;
    private final int THURSDAY_COLUMN = 1;
    private final int FRIDAY_COLUMN = 2;
    private final int SUNDAY_COLUMN = 3;
    private final int EXPERIENCE_COLUMN = 4;
    private final int PARTNER_COLUMN = 5;
    private final int BIRTHDAY_COLUMN = 6;

    @Override
    public List<Mini> loadMinis() {
        Path path = Paths.get(MINIS_PATH);

        List<Mini> minis = new ArrayList<>();
        try {
            String csvString = Files.readString(path);
            String[] lines = csvString.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] lineParts = line.split(";");

                MiniBuilder mini = new MiniBuilder();
                mini
                        .setName(lineParts[NAME_COLUMN])
                        .setExperience(Integer.parseInt(lineParts[EXPERIENCE_COLUMN]))
                        .setAvailableDays(getWeekdaysFromLine(lineParts))
                        .setBirthdate(getBirthdate(lineParts));

                minis.add(mini.build());
            }

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];

                String[] parts = line.split(";");
                if (parts[5].equals("")) continue;

                Mini self = minis
                        .stream()
                        .filter(mini -> mini.getName().equals(parts[0]))
                        .findAny()
                        .get();

                List<String> partners = Arrays.asList(parts[PARTNER_COLUMN].split(":"));
                partners.forEach(
                        partner -> minis.stream().filter(
                                        mini -> mini.getName().equals(partner.trim())
                                )
                                .findAny()
                                .ifPresent(mini -> self.getPartners().add(mini))
                );
            }
            return minis;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Calendar getBirthdate(String[] lineParts) {
        DateFormat sourceFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar birthdate = Calendar.getInstance();
        try {
            birthdate.setTime(sourceFormat.parse(lineParts[BIRTHDAY_COLUMN]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthdate;
    }

    private List<Weekday> getWeekdaysFromLine(String[] parts) {
        List<Weekday> weekdays = new ArrayList<>();
        if (Objects.equals(parts[THURSDAY_COLUMN], "") || Objects.equals(parts[1], "+")) {
            weekdays.add(Weekday.THURSDAY);
        }
        if (Objects.equals(parts[FRIDAY_COLUMN], "") || Objects.equals(parts[2], "+")) {
            weekdays.add(Weekday.FRIDAY);
        }
        if (Objects.equals(parts[SUNDAY_COLUMN], "") || Objects.equals(parts[3], "+")) {
            weekdays.add(Weekday.SUNDAY);
        }
        return weekdays;
    }
}
