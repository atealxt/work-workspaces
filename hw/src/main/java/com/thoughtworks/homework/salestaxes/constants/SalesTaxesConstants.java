package com.thoughtworks.homework.salestaxes.constants;

import java.math.BigDecimal;

/**
 * Cash register constant class.
 *
 * @author iamlusuo@gmail.com
 */
public final class SalesTaxesConstants {

    private SalesTaxesConstants() {}

    public final static BigDecimal RATE_GENERAL = new BigDecimal("0.1");
    public final static BigDecimal RATE_IMPORTED = new BigDecimal("0.05");

    public final static BigDecimal RATE_ROUNDED_MIN = new BigDecimal("0.05");

    public final static BigDecimal ZERO_DOT_ONE = new BigDecimal("0.1");
}
