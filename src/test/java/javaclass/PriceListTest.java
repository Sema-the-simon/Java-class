package javaclass;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {
    PriceList PriceList() {
        Map<Product, Price> map = new HashMap<>();
        map.put(new Product("Яйца куринные", 10), new Price(4937));
        map.put(new Product("Сыр", 4), new Price(18002));
        map.put(new Product("Молоко", 3), new Price(5039));
        map.put(new Product("Кошачий корм Вискас", 21), new Price(2000));
        map.put(new Product("Кошачий корм Китикэт", 22), new Price(2415));
        map.put(new Product("Кошачий корм Шэба", 23), new Price(3550));
        map.put(new Product("Порошок", 3228), new Price(8069));
        return new PriceList(map);
    }

    @Test
    void getProduct() {
        assertEquals(new Product("Сыр", 4), PriceList().getProduct(4).get());
    }

    @Test
    void addProductAndPrice() {
        Product product = new Product("Яйца куринные", 10);
        Price price = new Price(6500);
        PriceList example = new PriceList(new HashMap<>());
        example.add(product, price);
        assertEquals(1, example.getPriceList().size());
    }

    @Test
    void priceChangeForeCode() {
        PriceList prl = PriceList();
        prl.priceChange(10, new Price(7850));
        assertEquals(new Price(7850), prl.getPriceList().get(new Product("Яйца куринные", 10)));
    }

    @Test
    void PriceChangeForeProduct() {
        PriceList prl = PriceList();
        Product product = new Product("Яйца куринные", 10);
        prl.priceChange(product, new Price(7850));
        assertEquals(new Price(7850), prl.getPriceList().get(new Product("Яйца куринные", 10)));
    }

    @Test
    void nameChangeForProduct() {
        PriceList prl = PriceList();
        Product product = new Product("Яйца куринные", 10);
        prl.nameChange(product, "Chicken eggs");
        assertEquals("Chicken eggs", prl.getProduct(10).get().getName());
        assertNull(prl.getProduct(51651).orElse(null));
    }

    @Test
    void nameChangeCode() {
        PriceList prl = PriceList();
        prl.nameChange(10, "Chicken eggs");
        assertEquals("Chicken eggs", prl.getProduct(10).get().getName());
    }


    @Test
    void deleteByCode() {
        PriceList prl = PriceList();
        PriceList res = new PriceList(new HashMap<>());
        res.add(new Product("Яйца куринные", 10), new Price(4937));
        res.add(new Product("Сыр", 4), new Price(18002));
        res.add(new Product("Молоко", 3), new Price(5039));
        res.add(new Product("Кошачий корм Вискас", 21), new Price(2000));
        res.add(new Product("Кошачий корм Китикэт", 22), new Price(2415));
        res.add(new Product("Порошок", 3228), new Price(8069));
        prl.delete(23);
        assertEquals(res, prl);
    }

    @Test
    void deleteByProduct() {
        PriceList prl = PriceList();
        PriceList res = new PriceList(new HashMap<>());
        res.add(new Product("Яйца куринные", 10), new Price(4937));
        res.add(new Product("Сыр", 4), new Price(18002));
        res.add(new Product("Молоко", 3), new Price(5039));
        res.add(new Product("Кошачий корм Вискас", 21), new Price(2000));
        res.add(new Product("Кошачий корм Китикэт", 22), new Price(2415));
        res.add(new Product("Порошок", 3228), new Price(8069));
        prl.delete(new Product("Кошачий корм Шэба", 23));
        assertEquals(res, prl);

    }

    @Test
    void deleteAllByProduct() {
        PriceList prl = PriceList();
        PriceList res = new PriceList(new HashMap<>());
        res.add(new Product("Яйца куринные", 10), new Price(4937));
        res.add(new Product("Сыр", 4), new Price(18002));
        res.add(new Product("Молоко", 3), new Price(5039));
        res.add(new Product("Порошок", 3228), new Price(8069));
        Set<Product> set = new HashSet<>();
        set.add(new Product("Кошачий корм Шэба", 23));
        set.add(new Product("Кошачий корм Вискас", 21));
        set.add(new Product("Кошачий корм Китикэт", 22));
        prl.deleteAll(set);
        assertEquals(res, prl);
    }

    @Test
    void purchasePrice() {
        PriceList prl = PriceList();
        Set<Integer> set = new HashSet<>();
        set.add(21);
        set.add(22);
        set.add(23);
        assertEquals(new Price(7965), prl.purchasePrice(set).get());
        PriceList res = new PriceList(new HashMap<>());
        res.add(new Product("Яйца куринные", 10), new Price(4937));
        res.add(new Product("Сыр", 4), new Price(18002));
        res.add(new Product("Молоко", 3), new Price(5039));
        res.add(new Product("Порошок", 3228), new Price(8069));
        assertNull(res.purchasePrice(set).orElse(null));
    }

    @Test
    void containsAll() {
        PriceList prl = PriceList();
        Set<Product> setTrue = new HashSet<>();
        setTrue.add(new Product("Кошачий корм Шэба", 23));
        setTrue.add(new Product("Кошачий корм Вискас", 21));
        setTrue.add(new Product("Кошачий корм Китикэт", 22));
        assertTrue(prl.containsAll(setTrue));
        Set<Product> setFalse = new HashSet<>();
        setFalse.add(new Product("Шэба", 223));
        assertFalse(prl.containsAll(setFalse));
    }

    @Test
    void containsProduct() {
        PriceList prl = PriceList();
        assertTrue(prl.containsProduct(new Product("Яйца куринные", 10)));
        assertFalse(prl.containsProduct(new Product("Шоколад", 13)));
    }

    @Test
    void testEquals() {
        PriceList prl = PriceList();
        PriceList a = new PriceList(new HashMap<>());
        a.add(new Product("Яйца куринные", 10), new Price(4937));
        a.add(new Product("Сыр", 4), new Price(18002));
        a.add(new Product("Молоко", 3), new Price(5039));
        a.add(new Product("Порошок", 3228), new Price(8069));
        PriceList b = new PriceList(new HashMap<>());
        b.add(new Product("Яйца куринные", 10), new Price(4937));
        b.add(new Product("Сыр", 4), new Price(18002));
        b.add(new Product("Молоко", 3), new Price(5039));
        b.add(new Product("Порошок", 3228), new Price(8069));
        assertEquals(b, a);
        assertEquals(a, b);
        PriceList example1 = new PriceList(new HashMap<>());
        example1.add(new Product("Яйца куринные", 10), new Price(4937));
        example1.add(new Product("Сыр", 4), new Price(18002));
        example1.add(new Product("Молоко", 3), new Price(5039));
        example1.add(new Product("Порошок", 3228), new Price(8069));
        assertNotEquals(prl, example1);
    }

    @Test
    void testHashCode() {
        PriceList a = new PriceList(new HashMap<>());
        a.add(new Product("Яйца куринные", 10), new Price(4937));
        a.add(new Product("Сыр", 4), new Price(18002));
        a.add(new Product("Молоко", 3), new Price(5039));
        a.add(new Product("Порошок", 3228), new Price(8069));
        PriceList b = new PriceList(new HashMap<>());
        b.add(new Product("Яйца куринные", 10), new Price(4937));
        b.add(new Product("Сыр", 4), new Price(18002));
        b.add(new Product("Молоко", 3), new Price(5039));
        b.add(new Product("Порошок", 3228), new Price(8069));
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testToString() {
        PriceList toStringTest = new PriceList(new HashMap<>());
        toStringTest.add(new Product("Яйца куринные", 10), new Price(4937));
        toStringTest.add(new Product("Кошачий корм", 20), new Price(2017));
        assertEquals("PriceList{Кошачий корм:20 => 20руб. 17коп.; Яйца куринные:10 => 49руб. 37коп.; }", toStringTest.toString());
    }
}