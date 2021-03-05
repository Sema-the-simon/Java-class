package javaclass;

import java.util.*;

/**
 * Вариант 11 -- прейскурант [Java].
 * Хранит списки товаров {@link Product} и их цен {@link Price}, для этого используется {@code HasMap}.
 * <p>Методы:
 * <p>Добавление товара и его цены: {@code add};
 * <p>Изменение цены товара по известному коду или по известному товару: {@code priceChange};
 * <p>Изменение имени товара по известному коду или по известному товару: {@code nameChange};
 * <p>Удаление товара по известному коду или по известному товару: {@code delete};
 * <p>Удаление всех товаров из множества {@code set()}: {@code deleteAll};
 * <p>Определение цены покупки по коду и количествуэкземпляров: {@code purchasePrice};
 * <p>Найти продукт по заданному коду: {@code getProduct};
 * <p>Узнать содержится ли продукт в Прэйскуранте: {@code containsProduct};
 * <p>Узнать содержатся ли продукты из множества {@code set()} в Прэйскуранте: {@code containsAll};
 */
public final class PriceList {
    private final Map<Product, Price> priceList = new HashMap<>();

    public PriceList(Map<Product, Price> map) {
        if (map != null) {
            priceList.putAll(map);
        }
    }

    public Map<Product, Price> getPriceList() {
        return new HashMap<>(priceList);
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
        if (isNull(product, price)) return false;
        priceList.put(product, price);
        return true;
    }

    private <T> boolean isNull(Product product, T other) {
        return product == null || other == null;
    }

    public boolean priceChange(Product product, Price newPrice) {
        if (isNull(product, newPrice) || !priceList.containsKey(product)) return false;
        priceList.replace(product, newPrice);
        return true;
    }

    public boolean priceChange(int code, Price newPrice) {
        Optional<Product> product = this.getProduct(code);
        product.ifPresent((thisProduct) -> priceChange(thisProduct, newPrice));
        return product.isPresent();
    }

    public boolean nameChange(Product product, String newName) {
        if (isNull(product, newName) || !priceList.containsKey(product)) return false;
        Price price = priceList.get(product);
        product.setName(newName);
        priceList.remove(product);
        priceList.put(product, price);
        return true;
    }

    public boolean nameChange(int code, String newName) {
        Optional<Product> product = this.getProduct(code);
        product.ifPresent((thisProduct) -> nameChange(thisProduct, newName));
        return product.isPresent();
    }

    public boolean delete(Product product) {
        if (product == null || !priceList.containsKey(product)) return false;
        priceList.remove(product);
        return true;
    }

    public boolean delete(int code) {
        Optional<Product> product = this.getProduct(code);
        product.ifPresent(this::delete);
        return product.isPresent();
    }

    public boolean deleteAll(Set<Product> setOfProducts) {
        if (!containsAll(setOfProducts)) return false;
        for (Product product : setOfProducts) {
            this.delete(product);
        }
        return true;
    }

    public Price purchasePrice(Map<Integer, Integer> mapOfCodes) {
        int res = 0;
        int kop = 0;
        for (Map.Entry<Integer, Integer> pair : mapOfCodes.entrySet()) {
            Product product = getProduct(pair.getKey()).orElseThrow(() ->
                    new NoSuchElementException("No such Product with code:" + pair.getKey()));
            Price price = priceList.get(product);
            kop += price.getKop();
            kop += price.getRub() * 100;
            kop *= pair.getValue();
            res += kop;
            kop = 0;
        }
        return new Price(res);
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
        for (Map.Entry<Product, Price> pair : priceList.entrySet()) {
            SB.append(pair.getKey());
            SB.append(" => ");
            SB.append(pair.getValue());
            SB.append("; ");
        }
        return SB.append("}").toString();
    }
}
