package com.thoughtworks.homework.salestaxes;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.homework.salestaxes.entity.Goods;
import com.thoughtworks.homework.salestaxes.entity.SalesTaxes;

/**
 * Cash register helper class, parse and format string.
 *
 * @author iamlusuo@gmail.com
 */
public final class CashRegisterHelper {

    private CashRegisterHelper() {}

    public final static List<Goods> parseInput(final String goods) throws CashRegisterException {
        if (goods == null) {
            throw new CashRegisterException("Input cannot be null!", CashRegisterException.ErrorCode.ERROR_PARAMETER);
        }
        final List<Goods> goodsList = new ArrayList<Goods>();
        final String[] goodsArr = goods.split(GOOD_LINE_SEPARATOR);
        for (final String s : goodsArr) {
            final Matcher matcher = PATTERN_INPUT.matcher(s);
            if (!matcher.matches()) {
                throw new CashRegisterException("Input format error: " + s,
                                                CashRegisterException.ErrorCode.ERROR_PARAMETER);
            }
            final int quantity = Integer.parseInt(PATTERN_COMMA.matcher(matcher.group(GROUP_QUANTITY)).replaceAll(""));
            final String originStr = matcher.group(GROUP_DESC);
            final BigDecimal price = new BigDecimal(PATTERN_COMMA.matcher(matcher.group(GROUP_PRICE)).replaceAll(""));
            boolean imported;
            String name;
            String strParse = originStr.trim().toLowerCase();
            strParse = strParse.substring(0, strParse.length() - LEN_CHAR_AT_AND_A_SPACE);
            final int indexOf = strParse.indexOf(OF);
            if (indexOf == -1) {
                // string contains " of "
                if (strParse.startsWith(IMPORTED)) {
                    // string like: "1 imported book at 12.49"
                    imported = true;
                    name = strParse.substring(IMPORTED.length());
                } else {
                    // string like: "1 book at 12.49"
                    imported = false;
                    name = strParse;
                }
            } else {
                // string not contains " of "
                if (strParse.startsWith(IMPORTED)) {
                    // string like: "1 imported bottle of perfume at 27.99"
                    imported = true;
                    name = strParse.substring(indexOf + OF.length());
                } else {
                    final String sTemp = strParse.substring(indexOf + OF.length());
                    if (sTemp.startsWith(IMPORTED)) {
                        // string like: "1 box of imported chocolates at 11.25"
                        imported = true;
                        name = sTemp.substring(IMPORTED.length());
                    } else {
                        // string like: "1 bottle of perfume at 18.99"
                        imported = false;
                        name = sTemp;
                    }
                }
            }
            final Goods.Type goodsType = Goods.Type.parse(name);
            final Goods e = new Goods(originStr, quantity, strParse, goodsType, price, imported);
            goodsList.add(e);
        }
        return goodsList;
    }

    public final static String formatOutput(final List<SalesTaxes> taxes) throws CashRegisterException {
        final StringBuilder sb = new StringBuilder();
        BigDecimal sumTaxes = ZERO;
        BigDecimal sumPrice = ZERO;
        final DecimalFormat formatterQuantity = new DecimalFormat(",##0");
        final DecimalFormat formatterPrice = new DecimalFormat(",##0.00");
        for (final SalesTaxes e : taxes) {
            final Goods goods = e.getGoods();
            final String originStr = goods.getOriginStr();
            final String originStrExceptAt = originStr.substring(0, originStr.length() - LEN_CHAR_AT_AND_TWO_SPACE);
            sb.append(formatterQuantity.format(goods.getQuantity())).append(originStrExceptAt);
            sb.append(": ").append(formatterPrice.format(e.getShelfPrice().doubleValue())).append(GOOD_LINE_SEPARATOR);
            sumTaxes = sumTaxes.add(e.getTaxes());
            sumPrice = sumPrice.add(goods.getPrice()).add(e.getTaxes());
        }
        sb.append("Sales Taxes: ").append(formatterPrice.format(sumTaxes.doubleValue())).append(GOOD_LINE_SEPARATOR);
        sb.append("Total: ").append(formatterPrice.format(sumPrice.doubleValue())).append(GOOD_LINE_SEPARATOR);
        return sb.toString();
    }

    private static final String GOOD_LINE_SEPARATOR = "\n";

    private static Pattern PATTERN_INPUT = Pattern.compile("^([\\d\\,]+)(\\s\\D*\\sat\\s)(([\\d\\,]*)\\.?\\d*)$");
    private static Pattern PATTERN_COMMA = Pattern.compile(",");
    private static final int GROUP_QUANTITY = 1;
    private static final int GROUP_DESC = 2;
    private static final int GROUP_PRICE = 3;

    private final static String IMPORTED = "imported ";
    private final static String OF = " of ";
    /** "at ".length() */
    private static final int LEN_CHAR_AT_AND_A_SPACE = 3;
    /** " at ".length() */
    private static final int LEN_CHAR_AT_AND_TWO_SPACE = 4;
}
