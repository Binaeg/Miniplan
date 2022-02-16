package Miniplan_2_0.Miniplan;

import Miniplan_2_0.POJO.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

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
