package com.thoughtworks.homework.salestaxes;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.salestaxes.entity.Goods;
import com.thoughtworks.homework.salestaxes.entity.SalesTaxes;

/**
 * <p>
 * PROBLEM TWO: SALES TAXES<br>
 * The cash register for calculating and recording sales transactions.
 * </p>
 *
 * Use example:<br>
 * <code>
 * CashRegister cashRegister = new CashRegister();<br>
 * cashRegister.appendGoods("1 book at 12.49"); // append 1 purchase item.<br>
 * cashRegister.appendGoods("1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50\n"); // append 2 purchase items.<br>
 * cashRegister.outputReceipt(); // get the receipt which lists the name of all the items and their price (including tax).<br>
 * </code>
 *
 * @author iamlusuo@gmail.com
 */
public class CashRegister {

    public void appendGoods(final String input) throws CashRegisterException {
        goods.addAll(CashRegisterHelper.parseInput(input));
    }

    public String outputReceipt() throws CashRegisterException {
        final List<SalesTaxes> salesTaxesInfo = SalesTaxesCalculator.calc(goods);
        return CashRegisterHelper.formatOutput(salesTaxesInfo);
    }

    private final List<Goods> goods = new ArrayList<Goods>();
}
