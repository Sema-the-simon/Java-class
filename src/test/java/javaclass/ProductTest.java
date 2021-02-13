package javaclass;

import org.junit.jupiter.api.Test;
import javaclass.Product.*;
import static org.junit.jupiter.api.Assertions.*;
class ProductTest {

    @Test
    void getName() {
        Product nametest = new Product("Филе куринное", 35309);
        assertEquals("Филе куринное", nametest.getName());

    }
    @Test
    void getCode() {
        Product codetest = new Product("Филе куринное", 35309);
        assertEquals(35309, codetest.getCode());
    }
    @Test
    void setName() {
        Product nameChangeTest = new Product("Печеьне", 1);
        nameChangeTest.setName("Филе куринное");
        assertEquals("Филе куринное", nameChangeTest.getName());
    }

    @Test
    void testEquals() {
        Product linktest = new Product("Печеьне", 1);
        Product a = linktest;
        Product b = linktest;
        assertEquals(b, a);
        Product examle1 = new Product("Филе куринное", 35309);
        Product exaple2 = new Product("Филе куринное", 35309);
        assertEquals(exaple2, examle1);
    }

    @Test
    void testHashCode() {
        Product examle1 = new Product("Филе куринное", 35309);
        Product exaple2 = new Product("Филе куринное", 35309);
        assertEquals(exaple2.hashCode(), examle1.hashCode());
    }

    @Test
    void testToString() {
        Product toStringTest = new Product("Филе куринное", 35309);
        assertEquals("Филе куринное:35309", toStringTest.toString());
    }
}