package org.workintech.library.enums;

public enum Discount {
    STUDENT("%15 indirim.", 0.15),
    OFFICER("%10 indirim.", 0.1),
    NORMAL_MEMBER("İndirim tanımlı değil.", 0.0);

    private final String type;
    private final double discountAmount;

    Discount(String type, double discountAmount) {
        this.type = type;
        this.discountAmount = discountAmount;
    }

    public String getType() {
        return type;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
