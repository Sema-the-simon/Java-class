package javaclass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreiskurantTest {
    Preiskurant priceList = new Preiskurant(
            Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
            Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
            Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
            Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
            Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
            Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
            Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
    );

    @Test
    void addProductAndPrice() {
        Product product = new Product("Яйца куринные", 10);
        Preiskurant.Price price = new Preiskurant.Price(6500);
        Preiskurant example = new Preiskurant();
        example.addProductAndPrice(product, price);
        assertEquals(1, example.getPriceList().size());
    }

    @Test
    void priceChangeForeCode() {
        priceList.priceChange(10, new Preiskurant.Price(7850));
        assertEquals(new Preiskurant.Price(7850), priceList.getPriceList().get(0).getValue());

    }

    @Test
    void PriceChangeForeProduct() {
        Product product = new Product("Яйца куринные", 10);
        priceList.priceChange(product, new Preiskurant.Price(7850));
        assertEquals(new Preiskurant.Price(7850), priceList.getPriceList().get(0).getValue());
    }

    @Test
    void nameChangeForProduct() {
        Product product = new Product("Яйца куринные", 10);
        priceList.nameChange(product, "Chiken eggs");
        assertEquals("Chiken eggs", priceList.getPriceList().get(0).getKey().getName());
    }
    @Test
    void nameChangeCode() {
        priceList.nameChange(10, "Chiken eggs");
        assertEquals("Chiken eggs", priceList.getPriceList().get(0).getKey().getName());
    }


    @Test
    void deleteByCode() {
        Preiskurant res = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
        );
        priceList.delete(20);
        assertEquals(res, priceList);

    }
    @Test
    void deleteByProduct() {
        Preiskurant res = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
        );
        priceList.delete(new Product("Кошачий корм", 20));
        assertEquals(res, priceList);

    }

    @Test
    void deleteAllByCode() {
        Preiskurant res = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
        );
        priceList.deleteAll(20);
        assertEquals(res, priceList);
    }

    @Test
    void deleteAllByProduct() {
        Preiskurant res = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
        );
        priceList.deleteAll(new Product("Кошачий корм", 20));
        assertEquals(res, priceList);
    }

    @Test
    void purchasePrice() {
        assertEquals("60руб. 51коп.", priceList.purchasePrice(20));
        Preiskurant res = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069)));
        assertNull(res.purchasePrice(20));
    }

    @Test
    void containsProduct() {
        assertTrue(priceList.containsProduct(new Product("Яйца куринные", 10)));
        assertFalse(priceList.containsProduct(new Product("Шоколад", 13)));
    }

    @Test
    void containsProductWithCode() {
        assertTrue(priceList.containsProduct(10));
        assertFalse(priceList.containsProduct(13));
    }
    @Test
    void testEquals() {
        Preiskurant a = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
        );
        Preiskurant b = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)),
                Preiskurant.createPair(new Product("Порошок", 3228), new Preiskurant.Price(8069))
        );
        assertEquals(b, a);
        assertEquals(a, b);
        Preiskurant d = new Preiskurant();
        Preiskurant e = new Preiskurant();
        assertEquals(d, e);
        Preiskurant example1 = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)));
        assertNotEquals(priceList, example1);
    }

    @Test
    void testHashCode() {
        Preiskurant example1 = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)));
        Preiskurant example2 = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Сыр", 4), new Preiskurant.Price(18002)),
                Preiskurant.createPair(new Product("Молоко", 3), new Preiskurant.Price(5039)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017)));
        assertEquals(example2.hashCode(), example1.hashCode());
    }

    @Test
    void testToString() {
        Preiskurant toStringTest = new Preiskurant(
                Preiskurant.createPair(new Product("Яйца куринные", 10), new Preiskurant.Price(4937)),
                Preiskurant.createPair(new Product("Кошачий корм", 20), new Preiskurant.Price(2017))
        );
        assertEquals("Preiskurant{Яйца куринные:10 to 49руб. 37коп.; Кошачий корм:20 to 20руб. 17коп.; }", toStringTest.toString());
    }
}