package org.workintech.library;



import org.workintech.library.enums.TypeOfBook;
import org.workintech.library.enums.Used;
import org.workintech.library.interfaces.IBook;

import java.util.Objects;



public  class Book {

    // kitapların genel özellikleri için validasyonlar eklenecek
    // kitabın türüne göre belirlenenen id değerinin başına typeCode eklenecek
    // typecode değerine göre kitapları türüne göre çağır

    private long bookId;
    private String bookName;
    private String author;
    private double price;
    private Used status;
    private  TypeOfBook typeOfBook;

    public Book(long bookId, String bookName, String author, double price, Used status, TypeOfBook typeOfBook) {
            setTypeOfBookId(bookId, typeOfBook);
            setBookName(bookName);
            setAuthor(author);
            setPrice(price);
            setStatus(status);
            this.typeOfBook = typeOfBook;
    }

    public void setBookName(String bookName) {
        if (bookName == null || bookName.trim().isEmpty()) {
            throw new IllegalArgumentException("Kitap adı boş olamaz");
        }
        this.bookName = bookName;

    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Yazar adı boş olamaz");
        }
        this.author = author;

    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Fiyat negatif olamaz");
        }
        this.price = price;
    }

    public void setStatus(Used status) {
        if (status == null) {
            throw new IllegalArgumentException("Durum boş olamaz");
        }
        this.status = status;
    }

    public void setTypeOfBook(TypeOfBook typeOfBook) {
        if (typeOfBook == null) {
            throw new IllegalArgumentException("Durum boş olamaz");
        }
        this.typeOfBook = typeOfBook;
    }

    private void setTypeOfBookId(long bookId, TypeOfBook typeOfBook) {
        String strId = typeOfBook.getTypeCode() + String.valueOf(bookId);
        this.bookId = Long.parseLong(strId);
    }

    public long getBookId() {
        return bookId;
    }

    public TypeOfBook getTypeOfBook() {
        return typeOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookName() {
        return bookName;
    }

    public Used getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return bookId == book.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookId);
    }

    @Override
    public String toString() {
        return  '\n' +
                "Book Id= " + bookId + '\n' +
                "Book name= " + bookName + '\n' +
                "Author= " + author + '\n' +
                "Price= " + price + '\n' +
                "Status= " + status + '\n' +
                "Type of book= " + typeOfBook + '\n' ;
    }
}
