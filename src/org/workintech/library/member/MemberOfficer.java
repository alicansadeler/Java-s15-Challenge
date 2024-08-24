package org.workintech.library.member;

import org.workintech.library.Book;
import org.workintech.library.enums.Discount;

public class MemberOfficer extends MemberRecord{

    private String officerBusinessName;
    private String officerPosition;

    public MemberOfficer(String memberTC, String memberFullName, String memberAdress, String memberPhone,
                         String membershipDate, Discount discount, String officerBusinessName, String officerPosition) {
        super(memberTC, memberFullName, memberAdress, memberPhone, membershipDate, discount);
        setOfficerBusinessName(officerBusinessName);
        setOfficerPosition(officerPosition);
    }

    public String getOfficerBusinessName() {
        return officerBusinessName;
    }

    public String getOfficerPosition() {
        return officerPosition;
    }

    public void setOfficerBusinessName(String officerBusinessName) {
        if (officerBusinessName.length() >= 5) {
            this.officerBusinessName = officerBusinessName;
        } else {
            throw new IllegalArgumentException("İş yeri bilgisi en az 5 karakter içermelidir.");
        }

    }

    public void setOfficerPosition(String officerPosition) {
        if (officerPosition.length() >= 5) {
            this.officerPosition = officerPosition;
        } else {
            throw new IllegalArgumentException("Pozisyon bilgisi en az 5 karakter içermelidir.");
        }
    }

    @Override
    public String createInvoice(Book book) {
        double result = book.getPrice() - (book.getPrice() * (super.getDiscount().getDiscountAmount()));
        return "Fatura tutarı(memur): " + result;
    }

    @Override
    public String toString() {
        return  '\n' +
                super.toString() +
                "Memur İş Yeri Bilgisi= " + officerBusinessName + '\n' +
                "Çalışılan Pozisyon= " + officerPosition + '\n' ;
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
