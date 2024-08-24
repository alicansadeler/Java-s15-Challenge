package org.workintech.library.member;

import org.workintech.library.Book;
import org.workintech.library.enums.Discount;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class MemberRecord {
    private String memberTC;
    private String memberFullName;
    private String memberAdress;
    private String memberPhone;
    private String membershipDate;
    private final Discount discount;


    private int numerOfBook;
    private Set<Book> ownedBooks;

    public MemberRecord(String memberTC, String memberFullName, String memberAdress,
                        String memberPhone, String membershipDate, Discount discount ) {
        setMemberTC(memberTC);
        setMemberFullName(memberFullName);
        setMemberAdress(memberAdress);
        setMemberPhone(memberPhone);
        setMembershipDate(membershipDate);
        this.discount = discount;
        ownedBooks = new HashSet<>();
    }

    public void setMemberTC(String memberTC) {
        if (memberTC.length() == 11) {
            this.memberTC = memberTC;
        } else {
            throw new IllegalArgumentException("TC numarası 11 haneli olmalıdır.");
        }
    }

    public void setMemberFullName(String memberFullName) {
        if (memberFullName != null && memberFullName.length() >= 8) {
            this.memberFullName = memberFullName;
        } else {
            throw new IllegalArgumentException("İsim en az 8 karakter içermelidir.");
        }
    }

    public void setMemberAdress(String memberAdress) {
        if (memberAdress != null && memberAdress.length() >= 10) {
            this.memberAdress = memberAdress;
        } else {
            throw new IllegalArgumentException("Adres en az 10 karakter içermelidir.");
        }

    }

    public void setMemberPhone(String memberPhone) {
        if (memberPhone != null && memberPhone.length() == 11) {
            this.memberPhone = memberPhone;
        } else {
            throw new IllegalArgumentException("Telefon numarası 11 haneli olmalıdır.");
        }

    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getMemberTC() {
        return memberTC;
    }

    public String getMemberFullName() {
        return memberFullName;
    }


    public Set<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public Discount getDiscount() {
        return discount;
    }

    public abstract String createInvoice(Book book);

    @Override
    public String toString() {
        return  '\n' +
                "Üye TC NO= " + memberTC + '\n' +
                "Üye Ad Soyad= " + memberFullName + '\n' +
                "Üye Adresi= " + memberAdress + '\n' +
                "Üye Telefon Numarası= " + memberPhone + '\n' +
                "Üye Kayıt Tarihi= " + membershipDate + '\n' +
                "Ödünç alınan kitap sayısı= " + ownedBooks.size() + '\n' +
                "Tanımlı İndirim= " + discount.getDiscountAmount();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MemberRecord that = (MemberRecord) object;
        return Objects.equals(memberTC, that.memberTC);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(memberTC);
    }
}
