package javaclass;

/**
 * Price - цена.
 * Хранит рубли и копейки - rub, kop
 */
public final class Price {
    private final int rub;
    private final int kop;

    public Price(int kop) {
        if (kop <= 0) throw new IllegalArgumentException("Price can not be negative");
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


