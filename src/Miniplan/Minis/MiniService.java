package Miniplan.Minis;

import Miniplan.POJO.Mini;

import java.util.List;

public class MiniService implements IMiniService{
    private final IMiniRepository miniRepository;

    public MiniService(IMiniRepository miniRepository) {
        this.miniRepository = miniRepository;
    }

    @Override
    public List<Mini> loadMinis() {
        return miniRepository.loadMinis();
    }
}
