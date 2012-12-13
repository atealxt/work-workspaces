package com.thoughtworks.homework.salestaxes;

import static com.thoughtworks.homework.salestaxes.constants.SalesTaxesConstants.RATE_GENERAL;
import static com.thoughtworks.homework.salestaxes.constants.SalesTaxesConstants.RATE_IMPORTED;
import static com.thoughtworks.homework.salestaxes.constants.SalesTaxesConstants.RATE_ROUNDED_MIN;
import static com.thoughtworks.homework.salestaxes.constants.SalesTaxesConstants.ZERO_DOT_ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.salestaxes.entity.Goods;
import com.thoughtworks.homework.salestaxes.entity.SalesTaxes;

/**
 * Sales taxes calculator.
 *
 * @author iamlusuo@gmail.com
 */
public final class SalesTaxesCalculator {

    public static List<SalesTaxes> calc(final List<Goods> goods) {
        final List<SalesTaxes> list = new ArrayList<SalesTaxes>(goods.size());
        for (final Goods e : goods) {
            final SalesTaxes salesTaxes = new SalesTaxes(e);

            BigDecimal taxRate = ZERO;
            if (e.isImported()) {
                taxRate = taxRate.add(RATE_IMPORTED);
            }
            if (e.getGoodsType() == Goods.Type.GENERAL) {
                taxRate = taxRate.add(RATE_GENERAL);
            }

            // calculate base sales taxes
            final BigDecimal taxes = e.getPrice().multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);

            // calculate price contains taxes
            final BigDecimal priceContainsTaxes = e.getPrice().add(taxes).setScale(2, BigDecimal.ROUND_HALF_UP);

            // calculate shelf price complement
            final BigDecimal subtractRoundUpNearest = getRoundUpSubtract(taxRate, priceContainsTaxes);

            salesTaxes.setShelfPrice(priceContainsTaxes.add(subtractRoundUpNearest));
            salesTaxes.setTaxes(taxes.add(subtractRoundUpNearest));

            list.add(salesTaxes);
        }
        return list;
    }

    /** np/100 rounded up to the nearest 0.05 */
    private static BigDecimal getRoundUpSubtract(final BigDecimal taxRate, final BigDecimal priceContainsTaxes) {
        if (taxRate == ZERO) {
            return ZERO;
        }
        // take second place after the decimal point
        final BigDecimal fractionalSecondPlace = priceContainsTaxes.remainder(ZERO_DOT_ONE)
                .setScale(2, BigDecimal.ROUND_DOWN);
        if (fractionalSecondPlace.compareTo(ZERO) > 0 && fractionalSecondPlace.compareTo(RATE_ROUNDED_MIN) < 0) {
            // rounded up
            return RATE_ROUNDED_MIN.subtract(fractionalSecondPlace);
        }
        return ZERO;
    }
}
