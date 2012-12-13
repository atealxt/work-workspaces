package com.thoughtworks.homework.salestaxes.entity;

import static com.thoughtworks.homework.salestaxes.constants.GoodsDictionary.DICT_GOODS_BOOK;
import static com.thoughtworks.homework.salestaxes.constants.GoodsDictionary.DICT_GOODS_FOOD;
import static com.thoughtworks.homework.salestaxes.constants.GoodsDictionary.DICT_GOODS_MEDICAL_PRODUCTS;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Purchase item entity.
 *
 * @author iamlusuo@gmail.com
 */
public class Goods {

    private final String originStr;
    private int quantity;
    private final String name;
    private final Type goodsType;
    private final BigDecimal price;
    private final boolean imported;

    public Goods(
            final String originStr,
            final int quantity,
            final String name,
            final Type goodsType,
            final BigDecimal price,
            final boolean imported) {
        super();
        this.originStr = originStr;
        this.quantity = quantity;
        this.name = name;
        this.goodsType = goodsType;
        this.price = price;
        this.imported = imported;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getOriginStr() {
        return originStr;
    }

    public boolean isImported() {
        return imported;
    }

    public String getName() {
        return name;
    }

    public Type getGoodsType() {
        return goodsType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public enum Type {
        GENERAL, BOOKS, FOOD, MEDICAL_PRODUCTS;

        private final static Map<String, Type> CACHE_GOODS_TYPE = new HashMap<String, Type>();

        static {
            for (final String s : DICT_GOODS_BOOK) {
                CACHE_GOODS_TYPE.put(s, Type.BOOKS);
            }
            for (final String s : DICT_GOODS_FOOD) {
                CACHE_GOODS_TYPE.put(s, Type.FOOD);
            }
            for (final String s : DICT_GOODS_MEDICAL_PRODUCTS) {
                CACHE_GOODS_TYPE.put(s, Type.MEDICAL_PRODUCTS);
            }
        }

        public static Type parse(final String name) {
            final Type t = CACHE_GOODS_TYPE.get(name);
            if (t != null) {
                return t;
            }
            return Type.GENERAL;
        }
    }
}
