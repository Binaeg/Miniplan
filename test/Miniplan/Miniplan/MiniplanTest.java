package Miniplan.Miniplan;

import org.junit.Before;

public class MiniplanTest {
    private Miniplan miniplan;

    @Before
    public void createMiniplan() {
        miniplan = new Miniplan();
    }

//    @Test
//    public void getMiniTest() {
//        Mini mini = miniplan.getMiniToDeploy(Weekday.THURSDAY, 1);
//        Assert.assertFalse(mini.getName().equals(""));
//    }
//
//    @Test
//    public void getMiniNoMiniWithSameAmountOfDeployments() {
//        Mini mini;
//        try {
//            mini = miniplan.getMiniToDeploy(Weekday.THURSDAY, 1);
//        } catch (NoSuchElementException e) {
//            Assert.assertEquals("No value present", e.getMessage());
//            mini = miniplan.getMiniToDeploy(Weekday.THURSDAY, 1);
//        }
//        Assert.assertFalse(mini.getName().equals(""));
//    }

}
