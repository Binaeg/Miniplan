package Miniplan_2_0.Termine;

import Miniplan_2_0.POJO.Termin;

import java.util.List;

public class TerminService implements ITerminService{
    private final ITerminRepository terminRepository;

    public TerminService(ITerminRepository terminRepository) {
        this.terminRepository = terminRepository;
    }

    @Override
    public List<Termin> loadTermine() {
        return terminRepository.loadTermine();
    }
}
