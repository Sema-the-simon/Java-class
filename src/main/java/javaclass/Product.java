package javaclass;

import java.util.Objects;

public final class Product {
    private String name;
    private final int code;

    public Product(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return name != null && name.equals(other.name) && code == other.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code)* 17;
    }

    @Override
    public String toString() {
        return name + ":" + code;
    }

}
