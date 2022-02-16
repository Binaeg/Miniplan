package Miniplan.Miniplan;

import Miniplan.Minis.*;
import Miniplan.POJO.*;
import Miniplan.Termine.*;

import java.util.*;
import java.util.stream.Collectors;

public class Miniplan {
    private final IMiniRepository miniRepository = new MiniRepository();
    private final IMiniService miniService = new MiniService(miniRepository);

    private final ITerminRepository terminRepository = new TerminRepository();
    private final ITerminService terminService = new TerminService(terminRepository);

    private List<Mini> minis = miniService.loadMinis();
    private List<Termin> termine = terminService.loadTermine();

    private int maxDeployments = 0;

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

    public List<Mini> getMinis() {
        return minis;
    }

    public List<Termin> getTermine() {
        return termine;
    }


}
