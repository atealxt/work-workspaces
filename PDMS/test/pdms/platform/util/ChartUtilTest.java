package pdms.platform.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.vo.A1300PieVo;
import pdms.components.vo.A1301ColumnVo;
import pdms.components.vo.A1302GAndSVo;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ChartUtilTest {

    public ChartUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPieXmlData method, of class ChartUtil.
     */
    @Test
    public void testGetPieXmlData() {
        System.out.println("getPieXmlData");

        List<A1300PieVo> vos = new ArrayList<A1300PieVo>();
        for (int i = 1; i <= 5; i++) {
            A1300PieVo vo = new A1300PieVo();
            vo.setTitle("title" + i);
            vo.setValue(Float.toString(new Random().nextFloat()));
            vo.setPull_out(new Random().nextBoolean());
            if (i == 2) {
                vo.setColor("#BBE579");
            }
            if (i == 3) {
                vo.setPattern("patterns/diagonal.swf");
            }
            if (i == 4) {
                vo.setPattern_color("#FFEE2E");
            }

            vos.add(vo);
        }

        String result = ChartUtil.getPieXmlData(vos);
        assertNotNull(result);
    }

    /**
     * Test of getColumnXmlData method, of class ChartUtil.
     */
    @Test
    public void testGetColumnXmlData() {
        System.out.println("getColumnXmlData");
        A1301ColumnVo vo = new A1301ColumnVo();

        List<A1302GAndSVo> sVo = new ArrayList<A1302GAndSVo>();
        for (int i = 0; i < 12; i++) {
            A1302GAndSVo voTemp = new A1302GAndSVo();
            voTemp.setXid(Integer.toString(i));
            voTemp.setValue("08年" + (i + 1) + "月");
            sVo.add(voTemp);
        }
        vo.setSVo(sVo);

        Random random = new Random();
        List<List<A1302GAndSVo>> gVos = new ArrayList<List<A1302GAndSVo>>();
        for (int i = 0; i < 2; i++) {
            List<A1302GAndSVo> listVoTemp = new ArrayList<A1302GAndSVo>();
            for (int j = 0; j < 12; j++) {
                A1302GAndSVo voTemp = new A1302GAndSVo();
                voTemp.setXid(Integer.toString(j));
                voTemp.setValue(Integer.toString(random.nextInt()));
                listVoTemp.add(voTemp);
            }
            gVos.add(listVoTemp);
        }
        vo.setGVos(gVos);

        String result = ChartUtil.getColumnXmlData(vo);
        assertNotNull(result);
    }
}