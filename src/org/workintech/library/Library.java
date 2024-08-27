package org.workintech.library;
import org.workintech.library.enums.Used;
import org.workintech.library.management.BookManagement;
import org.workintech.library.management.LibrarianManagement;
import org.workintech.library.management.MemberManagement;
import org.workintech.library.member.MemberRecord;
import java.util.*;

public class Library  {

    private final String libraryName;
    private final String libraryAddress;
    public BookManagement bookManagement;
    public MemberManagement memberManagement;
    public LibrarianManagement librarianManagement;


    public static final int MAX_BOOK_LIMIT = 5;

    public Library(String libraryName, String libraryAddress, Librarian librarian, Book... books) {
        if (isValidLibrary(libraryName, libraryAddress)) {
            this.libraryName = libraryName;
            this.libraryAddress = libraryAddress;
            this.bookManagement = new BookManagement();
            this.memberManagement = new MemberManagement();
            this.librarianManagement = new LibrarianManagement();

            librarianManagement.registeredLibrarians(librarian);
            bookManagement.addBook(books);
        } else {
            throw new IllegalArgumentException("Geçersiz kütüphane bilgisi..");
        }
    }

    private boolean isValidLibrary(String libraryName, String libraryAddress) {
        return  libraryName != null && libraryName.length() > 4 &&
                libraryAddress != null && libraryAddress.length() > 10;
    }


    public void addBookToMember(MemberRecord memberRecord, long bookId) {
        if (memberRecord.getOwnedBooks().size() > MAX_BOOK_LIMIT) {
            System.out.println("Maks. kitap limitine ulaşılmış");
            return;
        }

        List<Book> books = bookManagement.getBook(bookId);
        if (!books.isEmpty()) {
            Book book = books.get(0);
            if (book.getStatus() != Used.IN_USE) {
                memberRecord.getOwnedBooks().add(book);
                book.setStatus(Used.IN_USE);
                System.out.println("Kitap üyeye eklendi: "
                        + book.getBookName() + " / "
                        + memberRecord.getMemberFullName()
                        + "\n" +
                        "Fatura: " + " " + memberRecord.createInvoice(book)
                );
            } else {
                memberManagement.checkBookInUse(book);
            }
        } else {
            System.out.println("Kitap bulunamadı");
        }
    }

    public void returnToBook(MemberRecord memberRecord, long bookId) {
        List<Book> books = bookManagement.getBook(bookId);
        Book book = books.get(0);
        if (book.getStatus() == Used.IN_USE) {
            memberRecord.getOwnedBooks().remove(bookId);
            book.setStatus(Used.NOT_USED);
            System.out.println("Kitap teslim alındı: "
                    + book.getBookName() + " / "
                    + memberRecord.getMemberFullName()
                    + "\n" +
                    "Ücret iadesi: " + " " + memberRecord.createInvoice(book)
            );
        } else {
            memberManagement.checkBookInUse(book);
        }
    }


    @Override
    public String toString() {
        return  "Kütüphanenin ismi= " + libraryName + '\n' +
                "Kütüphanenin Adresi= " + libraryAddress + '\n' +
                "Kütüphaneciler= " + librarianManagement.librarianMap + '\n' +
                "Kitaplar= " + '\n' + bookManagement.booksMap + '\n';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Library library = (Library) object;
        return Objects.equals(libraryName, library.libraryName) && Objects.equals(libraryAddress, library.libraryAddress) && Objects.equals(bookManagement, library.bookManagement) && Objects.equals(memberManagement, library.memberManagement) && Objects.equals(librarianManagement, library.librarianManagement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryName, libraryAddress, bookManagement, memberManagement, librarianManagement);
    }
}

