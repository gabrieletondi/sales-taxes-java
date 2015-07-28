package it.gabrieletondi.salestaxes;

public class Tax {

    private final int rate;

    public int getRate() {
        return rate;
    }

    private Tax(int rate) {
        this.rate = rate;
    }

    public static Tax withRate(int rate) {
        return new Tax(rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tax tax = (Tax) o;

        return rate == tax.rate;
    }

    @Override
    public int hashCode() {
        return rate;
    }
}
