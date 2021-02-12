package javaclass;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public final class Preiskurant {
    private final List<Entry<Product, Price>> pricelist = new ArrayList<>();

    private static final class Price {
        private final int rub;
        private final int kop;

        public Price(int kop) {
            rub = kop / 100;
            this.kop = kop;
        }

        public int getRub() {
            return rub;
        }

        public int getKop() {
            return kop;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Price)) return false;
            Price price = (Price) o;
            return getRub() == price.getRub() && getKop() == price.getKop();
        }

        @Override
        public int hashCode() {
            return 11 * getRub() + 83 * getKop();

        }

        @Override
        public String toString() {
            return getRub() + "руб. " + getKop() + "коп.";
        }
    }

    public Entry<Product, Price> createEntry(Product product, Price price) {
        return new AbstractMap.SimpleEntry<>(product, price);
    }

    public Boolean addProductAndPrice(Product product, Price price) {
        if (product == null) return false;
        pricelist.add(createEntry(product, price));
        return true;
    }

    public boolean priceChange(Product product, Price newprice) {
        if (!containsProduct(product)) return false;
        for (Entry pair : pricelist) {
            if (pair.getKey() == product) {
                pair.setValue(newprice);
            }
        }
        return true;
    }

    public boolean nameChange(Product product, Price newprice) {
        if (!containsProduct(product)) return false;
        for (Entry pair : pricelist) {
            if (pair.getKey() == product) {
                pair.setValue(newprice);
            }
        }
        return true;
    }
    public boolean delite(Product product) {
        if (!pricelist.contains(product)) return false;
        pricelist.remove(product);
        return true;
    }

    public boolean containsProduct(Product product) {
        for (Entry pair : pricelist) {
            if (pair.getKey() == product) return true;
        }
        return false;
    }
}
