/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class DistancevectorTest {

    public DistancevectorTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

        List<String> topology = new ArrayList<String>();
        topology.add("## X a");
        topology.add("# Y b");

        //test1
//        topology.add("a b 3");
//        topology.add("b a 3");
//        topology.add("a c 23");
//        topology.add("c a 23");
//        topology.add("b c 2");
//        topology.add("c b 2");
//        topology.add("c d 5");
//        topology.add("d c 5");

        //test2
        topology.add("a b 2");
        topology.add("a c 1");
        topology.add("b c 2");
        topology.add("b d 4");
        topology.add("d e 2");
        topology.add("c e 6");
        topology.add("d f 3");
        topology.add("e f 2");
        topology.add("c d 4");

        LabUtil.TOPOLOGY = topology;
        //Distancevector.analysis();
        System.out.println(LabUtil.TOPOLOGY);
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(LabUtil.getCurrentShortestPath());
        System.out.println(LabUtil.getCurrentShortestPath());
        System.out.println(LabUtil.getCurrentShortestPath());
        System.out.println(LabUtil.getCurrentShortestPath());
        System.out.println(LabUtil.getCurrentShortestPath());
        System.out.println(new Date(System.currentTimeMillis()));
    }
}