package javaclass;

import java.util.*;

public final class Preiskurant {
    private final List<Pair<Product, Price>> priceList = new ArrayList<>();

    @SafeVarargs
    public Preiskurant(Pair<Product,Price>... pairs) {
        if (pairs != null){
            for (Pair<Product, Price> pair: pairs) {
                this.addProductAndPrice(pair);
            }
        }
    }

    public List<Pair<Product, Price>> getPriceList(){
        return priceList;
    }

    public static final class Price {
        private final int rub;
        private final int kop;

        public Price(int kop) {
            rub = kop / 100;
            this.kop = kop % 100;
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

    public static Pair<Product, Price> createPair(Product product, Price price) {
        return new Pair<>(product, price);
    }

    public Boolean addProductAndPrice(Pair<Product, Price> pair) {
        if (pair == null) return false;
        priceList.add(pair);
        return true;
    }
    public Boolean addProductAndPrice(Product product, Price price) {
        if (product == null) return false;
        priceList.add(createPair(product, price));
        return true;
    }

    public boolean priceChange(Product product, Price newprice) {
        if (!containsProduct(product)) return false;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().equals(product)) {
                pair.setValue(newprice);
            }
        }
        return true;
    }

    public boolean priceChange(int code, Price newprice) {
        if (!containsProduct(code)) return false;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().getCode() == code) {
                pair.setValue(newprice);
            }
        }
        return true;
    }

    public boolean nameChange(Product product, String newname) {
        if (!containsProduct(product)) return false;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().equals(product)) {
                pair.getKey().setName(newname);
            }
        }
        return true;
    }
    public boolean nameChange(int code, String newname) {
        if (!containsProduct(code)) return false;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().getCode() == code) {
                pair.getKey().setName(newname);
            }
        }
        return true;
    }

    public boolean delete(Product product) {
        if (!containsProduct(product)) return false;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().equals(product)) {
                priceList.remove(pair);
                break;
            }
        }
        return true;
    }

    public boolean deleteAll(Product product) {
        if (!containsProduct(product)) return false;
        priceList.removeIf(pair -> pair.getKey().equals(product));
        return true;
    }

    public boolean delete(int code) {
        if (!containsProduct(code)) return false;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().getCode() == code) {
                priceList.remove(pair);
                break;
            }
        }
        return true;
    }

    public boolean deleteAll(int code) {
        if (!containsProduct(code)) return false;
        priceList.removeIf(pair -> pair.getKey().getCode() == code);
        return true;
    }

    public String purchasePrice(int code) {
        int kop = 0;
        if (!containsProduct(code)) return null;
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().getCode() == code) {
                kop += pair.getValue().getRub() * 100 ;
                kop += pair.getValue().getKop() ;
            }
        }
        return new Price(kop).toString();
    }

    public boolean containsProduct(Product product) {
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().equals(product)) return true;
        }
        return false;
    }

    public boolean containsProduct(int code) {
        for (Pair<Product, Price> pair : priceList) {
            if (pair.getKey().getCode() == code) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Preiskurant)) return false;
        Preiskurant that = (Preiskurant) o;
        return this.getPriceList().equals(that.getPriceList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriceList());
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("Preiskurant{");
        for (Pair<Product, Price> pair:this.getPriceList()) {
            SB.append(pair);
            SB.append("; ");
        }
        return SB.append("}").toString();
    }
}
