package javaclass;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ProductTest {

    @Test
    void constructorTest() {
        Product test = new Product("Филе куринное", 35309);
        assertEquals("Филе куринное", test.getName());
        assertEquals(35309, test.getCode());
        assertThrows(IllegalArgumentException.class, () -> new Product("IllegalProduct", -15));
    }

    @Test
    void setName() {
        Product nameChangeTest = new Product("Печеьне", 1);
        nameChangeTest.setName("Филе куринное");
        assertEquals("Филе куринное", nameChangeTest.getName());
    }

    @Test
    void testEquals() {
        Product a = new Product("Печеьне", 1);
        Product b = new Product("Печеьне", 1);
        assertEquals(b, a);
        assertEquals(a, b);
        Product example1 = new Product("Филе куринное", 35409);
        Product example2 = new Product("Филе куринное", 35309);
        assertNotEquals(example2, example1);
    }

    @Test
    void testHashCode() {
        Product example1 = new Product("Филе куринное", 35309);
        Product example2 = new Product("Филе куринное", 35309);
        assertEquals(example2.hashCode(), example1.hashCode());
    }

    @Test
    void testToString() {
        Product toStringTest = new Product("Филе куринное", 35309);
        assertEquals("Филе куринное:35309", toStringTest.toString());
    }
}