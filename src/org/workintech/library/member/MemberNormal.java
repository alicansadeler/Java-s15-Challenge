package org.workintech.library.member;

import org.workintech.library.Book;
import org.workintech.library.enums.Discount;

public class MemberNormal extends MemberRecord{

    private String job;

    public MemberNormal(String memberTC, String memberFullName, String memberAdress, String memberPhone, String membershipDate, Discount discount, String job) {
        super(memberTC, memberFullName, memberAdress, memberPhone, membershipDate, discount);
        this.job = job;
    }

    @Override
    public String createInvoice(Book book) {
        double result = book.getPrice() - (book.getPrice() * (super.getDiscount().getDiscountAmount()));
        return "Fatura tutarı(normal): " + result;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Çalışılan Pozisyon= " + this.job;
    }
}
