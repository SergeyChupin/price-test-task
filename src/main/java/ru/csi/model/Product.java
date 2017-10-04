package ru.csi.model;

import java.util.Collection;

public class Product {

    private int code;
    private Collection<Price> prices;

    public Product(
            int code,
            Collection<Price> prices) {
        this.code = code;
        this.prices = prices;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Collection<Price> getPrices() {
        return prices;
    }

    public void setPrices(Collection<Price> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (code != product.code) return false;
        return prices != null ? prices.equals(product.prices) : product.prices == null;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product [code=" + code +
                ", prices=" + prices +
                ']';
    }
}
