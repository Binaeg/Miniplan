package Miniplan;

import Miniplan.Miniplan.Miniplan;
import Miniplan.POJO.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

public class Main {
    private static Miniplan miniplan = new Miniplan();

    public static void main(String[] args) {
        miniplan.getTermine()
                .stream()
                .sorted((o1, o2) -> {
                    DateFormat sourceFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    try {
                        c1.setTime(sourceFormat.parse(o1.getDate()));
                        c2.setTime(sourceFormat.parse(o2.getDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return c1.compareTo(c2);
                })
                .forEach(termin -> {
                    for (int i = 0; i < termin.getAmountOfMinis(); i++) {
                        Mini miniToDeploy = miniplan.getMiniToDeploy(termin, 2-(i%2));

                        termin.getDeployedMinis().add(miniToDeploy);
                        i = miniplan.deployPartners(termin, i, miniToDeploy);
                    }
                });

        for (Termin termin : miniplan.getTermine()) {
            termin.getDeployedMinis().sort(Comparator.comparing(Mini::getBirthdate));
            System.out.printf("%s, %s\n" , termin.getWeekday().getName(), termin.getDate());
            System.out.println(termin.getTime() + "\t\t" + termin.deployedMinisToString());
        }

        System.out.println();

    }


}
