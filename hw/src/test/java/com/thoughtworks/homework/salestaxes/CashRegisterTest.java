package com.thoughtworks.homework.salestaxes;

import junit.framework.Assert;

import org.junit.Test;

/**
 * TestCase for <b>PROBLEM TWO: SALES TAXES</b>.
 *
 * @author iamlusuo@gmail.com
 */
public class CashRegisterTest {

    /** Test input 1 */
    @Test
    public void testExample1() {
        System.out.println("Input 1:");
        System.out.println(INTPUT_1);

        final CashRegister cashRegister = new CashRegister();
        cashRegister.appendGoods(INTPUT_1);
        final String output1 = cashRegister.outputReceipt();
        Assert.assertEquals(EXPECT_OUTPUT_1, output1);

        System.out.println("Output 1:");
        System.out.println(output1);
    }

    /** Test input 2 */
    @Test
    public void testExample2() {
        System.out.println("Input 2:");
        System.out.println(INTPUT_2);

        final CashRegister cashRegister = new CashRegister();
        cashRegister.appendGoods(INTPUT_2);
        final String output2 = cashRegister.outputReceipt();
        Assert.assertEquals(EXPECT_OUTPUT_2, output2);

        System.out.println("Output 2:");
        System.out.println(output2);
    }

    /** Test input 3 */
    @Test
    public void testExample3() {
        System.out.println("Input 3:");
        System.out.println(INTPUT_3);

        final CashRegister cashRegister = new CashRegister();
        cashRegister.appendGoods(INTPUT_3);
        final String output3 = cashRegister.outputReceipt();
        Assert.assertEquals(EXPECT_OUTPUT_3, output3);

        System.out.println("Output 3:");
        System.out.println(output3);
    }

    /** Test else case with input valid */
    @Test
    public void testImported() {
        System.out.println("Input 4:");
        System.out.println(INTPUT_4);

        final CashRegister cashRegister = new CashRegister();
        cashRegister.appendGoods(INTPUT_4);
        final String output4 = cashRegister.outputReceipt();
        Assert.assertEquals(EXPECT_OUTPUT_4, output4);

        System.out.println("Output 4:");
        System.out.println(output4);
    }

    /** Test else case with input valid */
    @Test
    public void testElseCase() {
        final CashRegister cashRegister = new CashRegister();
        cashRegister.appendGoods("1 book at 12");
        cashRegister.appendGoods("1 book at 0.123");
        cashRegister.appendGoods("1 book at .491");
        cashRegister.appendGoods("1 book at 12.");
        cashRegister.appendGoods("888,888 books at 9,999,999,999,999.99");
    }

    /** Test input with error parameter */
    @Test
    public void testErrorParameter() {
        callWithErrorParameter(null);
        callWithErrorParameter("hello world!");
        callWithErrorParameter("1.2 book at 12.491");
        callWithErrorParameter("1 book at 12.49.345");
        callWithErrorParameter("1 imported box of chocolates att 10.00");// wrong spell "att"
        callWithErrorParameter("1 book at 12.491 music CD at 14.99");// forgot \n
    }

    private void callWithErrorParameter(final String s) {
        final CashRegister cashRegister = new CashRegister();
        try {
            cashRegister.appendGoods(s);
            Assert.fail();
        } catch (final CashRegisterException e) {
            switch (e.getErrorCode()) {
                case ERROR_PARAMETER:
                    // ok
                    break;
                default:
                    Assert.fail();
                    break;
            }
        }
    }

    private static final String INTPUT_1 = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85\n";
    private static final String INTPUT_2 = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50\n";
    private static final String INTPUT_3 = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25\n";
    private static final String INTPUT_4 = "2 imported books at 12.49\n";

    private static final String EXPECT_OUTPUT_1 = "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83\n";
    private static final String EXPECT_OUTPUT_2 = "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15\n";
    private static final String EXPECT_OUTPUT_3 = "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 box of imported chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68\n";
    private static final String EXPECT_OUTPUT_4 = "2 imported books: 13.15\nSales Taxes: 0.66\nTotal: 13.15\n";
}
