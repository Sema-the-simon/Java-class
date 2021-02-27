package javaclass;

import java.util.*;

public final class PriceList {
    private final Map<Product, Price> priceList = new HashMap<>();

    public PriceList(Map<Product, Price> map) {
        if (map != null) {
            priceList.putAll(map);
        }
    }


    public Map<Product, Price> getPriceList() {
        return priceList;
    }

    public Optional<Product> getProduct(int code) {
        for (Product product : priceList.keySet()) {
            if (product.getCode() == code) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public Boolean add(Product product, Price price) {
        if (product == null || price == null) return false;
        priceList.put(product, price);
        return true;
    }

    public boolean priceChange(Product product, Price newPrice) {
        if (product == null || newPrice == null || !priceList.containsKey(product)) return false;
        priceList.replace(product, newPrice);
        return true;
    }

    public boolean priceChange(int code, Price newPrice) {
        Optional<Product> product = this.getProduct(code);
        if (product.isEmpty()) return false;
        priceChange(product.get(), newPrice);
        return true;
    }

    public boolean nameChange(Product product, String newName) {
        if (product == null || newName == null ||!priceList.containsKey(product)) return false;
        Price price = priceList.get(product);
        product.setName(newName);
        priceList.remove(product);
        priceList.put(product, price);
        return true;
    }

    public boolean nameChange(int code, String newName) {
        Optional<Product> product = this.getProduct(code);
        if (product.isEmpty()) return false;
        nameChange(product.get(), newName);
        return false;
    }

    public boolean delete(Product product) {
        if (product == null ||!priceList.containsKey(product)) return false;
        priceList.remove(product);
        return true;
    }

    public boolean deleteAll(Set<Product> setOfProducts) {
        if (!containsAll(setOfProducts)) return false;
        for (Product product : setOfProducts) {
            this.delete(product);
        }
        return true;
    }

    public boolean delete(int code) {
        Optional<Product> product = this.getProduct(code);
        if (product.isEmpty()) return false;
        delete(product.get());
        return true;
    }

    public Optional<Price> purchasePrice(Set<Integer> setOfCodes) {
        int kop = 0;
        Set<Product> setOfProducts = new HashSet<>();
        for (Integer code : setOfCodes) {
            Optional<Product> product = getProduct(code);
            if (product.isEmpty()) return Optional.empty();
            setOfProducts.add(product.get());
        }
        for (Map.Entry<Product, Price> pair: priceList.entrySet()) {
            if (setOfProducts.contains(pair.getKey())) {
                kop += pair.getValue().getRub() * 100;
                kop += pair.getValue().getKop();
            }
        }
        return Optional.of(new Price(kop));
    }

    public boolean containsAll(Set<Product> setOfProducts) {
        for (Product product : setOfProducts) {
        if (!priceList.containsKey(product)) return false;
        }
        return true;
    }

    public boolean containsProduct(Product product) {
        return priceList.containsKey(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceList)) return false;
        PriceList that = (PriceList) o;
        return this.getPriceList().equals(that.getPriceList());
    }

    @Override
    public int hashCode() {
        return 115 * getPriceList().hashCode() - 113;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("PriceList{");
        for (Map.Entry<Product, Price> pair: priceList.entrySet()) {
            SB.append(pair.getKey());
            SB.append(" => ");
            SB.append(pair.getValue());
            SB.append("; ");
        }
        return SB.append("}").toString();
    }
}
