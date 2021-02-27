package javaclass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void constructorTest() {
        Price test = new Price(2950);
        assertEquals(29, test.getRub());
        assertEquals(50, test.getKop());
        assertThrows(IllegalArgumentException.class, () -> new Price(-15));
    }

    @Test
    void testEquals() {
        Price a = new Price(225);
        Price b = new Price(225);
        assertEquals(b, a);
        assertEquals(a, b);
        Price example1 = new Price(1515);
        Price example2 = new Price(8882);
        assertNotEquals(example2, example1);
    }

    @Test
    void testHashCode() {
        Price a = new Price(225);
        Price b = new Price(225);
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    void testToString() {
        Price a = new Price(225);
        assertEquals("2руб. 25коп.", a.toString());
    }
}