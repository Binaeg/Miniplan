package Miniplan_2_0.Minis;

import Miniplan_2_0.POJO.Mini;
import org.junit.Test;

import java.util.List;

public class MiniRepositoryTest {
    @Test
    public void testLoadMinis() {
        IMiniRepository repository = new MiniRepository();
        List<Mini> minis = repository.loadMinis();

    }
}
