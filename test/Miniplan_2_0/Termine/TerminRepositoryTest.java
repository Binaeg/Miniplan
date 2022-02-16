package Miniplan_2_0.Termine;

import Miniplan_2_0.POJO.Termin;
import org.junit.Test;

import java.util.List;

public class TerminRepositoryTest {
    @Test
    public void testTerminRepository() {
        ITerminRepository repository = new TerminRepository();
        List<Termin> termine = repository.loadTermine();
    }
}
