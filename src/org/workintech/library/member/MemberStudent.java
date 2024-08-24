package org.workintech.library.member;

import org.workintech.library.Book;
import org.workintech.library.enums.Discount;

public class MemberStudent extends MemberRecord{

    private String studentNo;
    private String studentFaculty;


    public MemberStudent(String memberTC, String memberFullName, String memberAdress, String memberPhone,
                         String membershipDate, Discount discount, String studentNo, String studentFaculty) {
        super(memberTC, memberFullName, memberAdress, memberPhone, membershipDate, discount);
        setStudentNo(studentNo);
        setStudentFaculty(studentFaculty);
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentNo(String studentNo) {
        if (studentNo.length() >= 9) {
            this.studentNo = studentNo;
        } else {
            throw new IllegalArgumentException("Öğrenci numarası en az 9 haneli olmalıdır.");
        }

    }

    public void setStudentFaculty(String studentFaculty) {
        if (studentFaculty.length() >= 5) {
            this.studentFaculty = studentFaculty;
        } else {
            throw new IllegalArgumentException("Fakülte bilgisi en az 5 karakter içermelidir.");
        }
    }

    @Override
    public String createInvoice(Book book) {
        double result = book.getPrice() - (book.getPrice() * (super.getDiscount().getDiscountAmount()));
        return "Fatura tutarı(öğrenci): " + result;
    }

    @Override
    public String toString() {
        return  '\n' +
                super.toString() +
                "Öğrenci Numarası= " + studentNo + '\n' +
                "Fakülte Bilgisi= " + studentFaculty + '\n';
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
