package com.thoughtworks.homework.salestaxes.entity;

import java.math.BigDecimal;

/**
 * Sales taxes entity.
 *
 * @author iamlusuo@gmail.com
 */
public class SalesTaxes {

    private final Goods goods;
    private BigDecimal taxes;
    private BigDecimal shelfPrice;

    public SalesTaxes(final Goods goods) {
        super();
        this.goods = goods;
    }

    public Goods getGoods() {
        return goods;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(final BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getShelfPrice() {
        return shelfPrice;
    }

    public void setShelfPrice(final BigDecimal shelfPrice) {
        this.shelfPrice = shelfPrice;
    }
}
