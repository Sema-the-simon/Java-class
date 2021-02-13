package javaclass;

import java.util.*;

public final class Preiskurant {
    private final List<Pair<Product, Price>> pricelist = new ArrayList<>();

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

    public static class Pair<K, V> {

        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return key.hashCode() ^ value.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> pairo = (Pair<?, ?>) o;
            return this.key.equals(pairo.getKey()) &&
                    this.value.equals(pairo.getValue());
        }

        @Override
        public String toString() {
            return getKey() + " to " + getValue();
        }
    }

    public Pair<Product, Price> createPair(Product product, Price price) {
        return new Pair<>(product, price);
    }

    public Boolean addProductAndPrice(Product product, Price price) {
        if (product == null) return false;
        pricelist.add(createPair(product, price));
        return true;
    }

    public boolean priceChange(Product product, Price newprice) {
        if (!containsProduct(product)) return false;
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey() == product) {
                pair.setValue(newprice);
            }
        }
        return true;
    }

    public boolean priceChange(int code, Price newprice) {
        if (!containsProduct(code)) return false;
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey().getCode() == code) {
                pair.setValue(newprice);
            }
        }
        return true;
    }

    public boolean nameChange(Product product, String newname) {
        if (!containsProduct(product)) return false;
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey() == product) {
                pair.getKey().setName(newname);
            }
        }
        return true;
    }

    public boolean delete(Product product) {
        if (!containsProduct(product)) return false;
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey() == product) {
                pricelist.remove(pair);
                break;
            }
        }
        return true;
    }

    public boolean deleteAll(Product product) {
        if (!containsProduct(product)) return false;
        pricelist.removeIf(pair -> pair.getKey() == product);
        return true;
    }

    public boolean delete(int code) {
        if (!containsProduct(code)) return false;
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey().getCode() == code) {
                pricelist.remove(pair);
                break;
            }
        }
        return true;
    }

    public boolean deleteAll(int code) {
        if (!containsProduct(code)) return false;
        pricelist.removeIf(pair -> pair.getKey().getCode() == code);
        return true;
    }

    public String purchasePrice(int code) {
        int kop = 0;
        if (!containsProduct(code)) throw  new IllegalArgumentException("This preiskurant does not contain Product:" + code);
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey().getCode() == code) {
                kop += pair.getValue().getRub() * 100 ;
                kop += pair.getValue().getKop() ;
            }
        }
        return new Price(kop).toString();
    }

    public boolean containsProduct(Product product) {
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey() == product) return true;
        }
        return false;
    }

    public boolean containsProduct(int code) {
        for (Pair<Product, Price> pair : pricelist) {
            if (pair.getKey().getCode() == code) return true;
        }
        return false;
    }
}
