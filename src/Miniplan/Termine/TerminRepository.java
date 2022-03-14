package Miniplan.Termine;

import Miniplan.POJO.Termin;
import Miniplan.POJO.TerminBuilder;
import Miniplan.POJO.Weekday;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TerminRepository implements ITerminRepository{

    private static String TERMINE_PATH = "./febmar.csv";

    private final int DATE_COLUMN = 0;
    private final int TIME_COLUMN = 2;
    private final int MINI_AMOUNT_COLUMN = 6;


    @Override
    public List<Termin> loadTermine() {
        Path path = Paths.get(TERMINE_PATH);
        try {
            String csvString = Files.readString(path);
            String[] lines = csvString.split("\n");

            List<Termin> termine = new ArrayList<>();

            TerminBuilder tb = new TerminBuilder();
            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split(";");
                tb
                        .setDate(line[DATE_COLUMN].replaceAll("\"", ""))
                        .setTime(line[TIME_COLUMN].replaceAll("\"", ""))
                        .setAmountOfMinis((int) line[MINI_AMOUNT_COLUMN].chars().filter(entries -> entries == '-').count())
                        .setWeekday(Weekday.getWeekdayFromDate(line[DATE_COLUMN].replaceAll("\"", "")));
                termine.add(tb.build());
            }
            return termine;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setTerminePath(String s) {
        TERMINE_PATH = s;
    }
}
