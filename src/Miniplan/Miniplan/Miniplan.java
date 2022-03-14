package Miniplan.Miniplan;

import Miniplan.GUI.GUI;
import Miniplan.Minis.*;
import Miniplan.POJO.*;
import Miniplan.Termine.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Miniplan {
    private IMiniRepository miniRepository;
    private IMiniService miniService;

    private ITerminRepository terminRepository;
    private ITerminService terminService;

    private List<Mini> minis;
    private List<Termin> termine;

    private int maxDeployments = 0;

    private GUI GUI;

    public Miniplan(GUI GUI) {
        this.GUI = GUI;
    }

    public void initMinis(boolean override) {
        if (minis == null || override) {
            try {
                miniRepository = new MiniRepository();
                miniService = new MiniService(miniRepository);
                minis = miniService.loadMinis();
                GUI.log("Minis erfolgreich geladen.");
            } catch (Exception e) {
                GUI.log("Fehler beim Minis laden: " + e.getMessage());
            }
        }
    }

    public void initTermine(boolean override) {
        if (termine == null || override) {
            try {
                terminRepository = new TerminRepository();
                terminService = new TerminService(terminRepository);
                termine = terminService.loadTermine();
                GUI.log("Termine erfolgreich geladen.");
            } catch (Exception e) {
                GUI.log("Fehler beim Termine laden: " + e.getMessage());
            }
        }
    }

    public void deployMinis() {
        initMinis(false);
        initTermine(false);
        try {
            getTermine()
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
                            Mini miniToDeploy = getMiniToDeploy(termin, 2 - (i % 2));

                            termin.getDeployedMinis().add(miniToDeploy);
                            i = deployPartners(termin, i, miniToDeploy);
                        }
                    });
        } catch (Exception e) {
            GUI.log("Fehler beim einteilen der Minis: " + e.getMessage());
        }
    }

    public Mini getMiniToDeploy(Termin termin, int experience) throws NoSuchElementException {
        List<Mini> avaiableMinis = getAvailableMinis(termin.getWeekday(), experience);
        Random r = new Random();

        Mini mini = avaiableMinis.get(r.nextInt(avaiableMinis.size()));
        while (termin.getDeployedMinis().contains(mini)) {
            mini = avaiableMinis.get(r.nextInt(avaiableMinis.size()));
        }
        mini.incrementAmountOfEntries();
        return mini;
    }

    private List<Mini> getAvailableMinis(Weekday weekday, int experience) {
        List<Mini> availableMinis;
        while (true) {
            availableMinis = minis
                    .stream()
                    .filter(mini -> mini.getAvailableDays().contains(weekday) && mini.getExperience() == experience && mini.getAmountOfEntries() == maxDeployments)
                    .collect(Collectors.toList());
            if (availableMinis.size() == 0) {
                maxDeployments++;
            } else {
                break;
            }
        }
        return availableMinis;
    }

    public int deployPartners(Termin termin, int i, Mini miniToDeploy) {
        List<Mini> partners = miniToDeploy.getPartners();
        if (!partners.isEmpty()) {
            int partnerCounter = 0;
            while ((i < (termin.getAmountOfMinis() - 1) && partnerCounter < partners.size())) {
                termin.getDeployedMinis().add(partners.get(partnerCounter).incrementAmountOfEntries());
                partnerCounter++;
                i++;
            }
        }
        return i;
    }

    public List<Termin> getTermine() {
        return termine;
    }

    public List<Mini> getMinis() {
        return minis;
    }
}
