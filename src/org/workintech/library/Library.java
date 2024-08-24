package org.workintech.library;

import org.workintech.library.enums.TypeOfBook;
import org.workintech.library.enums.Used;
import org.workintech.library.interfaces.IBook;
import org.workintech.library.member.MemberRecord;

import java.util.*;
import java.util.stream.Collectors;


public class Library implements IBook {

    private final String libraryName;
    private final String libraryAddress;

    private  Map<Long, Librarian> librarianMap;
    private  Map<Long, Book> booksMap;
    private  Map<String, MemberRecord> memberMap;

    private static final int MAX_BOOK_LIMIT = 5;

    public Library(String libraryName, String libraryAddress, Librarian librarian, Book... books) {
        if (isValidLibrary(libraryName, libraryAddress)) {
            this.libraryName = libraryName;
            this.libraryAddress = libraryAddress;
            booksMap = new HashMap<>();
            librarianMap = new HashMap<>();
            memberMap = new HashMap<>();
            registeredLibrarians(librarian);
            addBook(books);
        } else {
            throw new IllegalArgumentException("Geçersiz kütüphane bilgisi..");
        }
    }

    private boolean isValidLibrary(String libraryName, String libraryAddress) {
        return  libraryName != null && libraryName.length() > 4 &&
                libraryAddress != null && libraryAddress.length() > 10;
    }

    private void registeredLibrarians(Librarian librarian) {
        if(librarianMap.containsKey(librarian.getLibrarianId())) {
            throw new IllegalArgumentException("Kütüphaneci sisteme önceden kaydedilmiş.");
        } else {
            librarianMap.put(librarian.getLibrarianId(), librarian);
        }
    }

    public  Map<Long, Librarian> getLibrarians() {
        return librarianMap;
    }

    public List<Book> getBook(long bookId) {
        return    booksMap.entrySet().stream().filter(entry -> {
            String strKey = String.valueOf(entry.getKey()).substring(1);
            long longKey = Long.parseLong(strKey);
            return longKey == bookId;
        }).map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public List<Book> getBook(String authorOrBookName) {
        List<Book> optionalBookAuthor = booksMap.values().stream().filter(book -> book.getAuthor().replaceAll(" ", "")
                .equalsIgnoreCase((authorOrBookName).replaceAll(" ", ""))).toList();
        List<Book> optionalBookName = booksMap.values().stream().filter(book -> book.getBookName().replaceAll(" ", "")
                .equalsIgnoreCase((authorOrBookName).replaceAll(" ", ""))).toList();

        if (!optionalBookAuthor.isEmpty()) {
            return optionalBookAuthor;
        } else if (!optionalBookName.isEmpty()) {
            return optionalBookName;
        } else {
            return null;
        }
    }

    public Map<Long, Book> getAllBooks() {
        return booksMap;
    }

    public List<Book> getBookGenre(TypeOfBook typeOfBook) {
        List<Book> bookGenre = new ArrayList<>();
        try {
            char typeChar = typeOfBook.getTypeCode().charAt(0);
            return booksMap.entrySet().stream().filter(entry -> String.valueOf(entry.getKey()).charAt(0) == typeChar).map(Map.Entry::getValue).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Kitap türünde hata var" + e.getMessage());
        }
        return bookGenre;
    }

    public String deleteBook(long bookId) {
        booksMap.entrySet().removeIf(entry -> {
            String strKey = String.valueOf(entry.getKey()).substring(1);
            long longKey = Long.parseLong(strKey);
            return longKey == bookId;
        });
        return "Kitap Silindi.";
    }

    public void addBook(Book... books) {
        Arrays.stream(books).forEach(book -> {
            String strId = book.getTypeOfBook().getTypeCode() + (book.getBookId());
            if (!booksMap.containsKey(Long.parseLong(strId))) {
                booksMap.put(book.getBookId(), book);
            } else {
                throw new IllegalArgumentException("Kitap sistemde zaten kayıtlı!");
            }
        });
    }

    public void addMember(MemberRecord memberRecord) {
        if (memberMap.putIfAbsent(memberRecord.getMemberTC(), memberRecord) != null) {
            throw new IllegalArgumentException("Üye sistemde kayıtlı");
        }
    }

    public void deleteMember(MemberRecord memberRecord) {
        if (memberMap.remove(memberRecord.getMemberTC()) != null) {
            System.out.println("Üye başarıyla silindi: " + memberRecord.getMemberFullName());
        } else {
            System.out.println("Üye bulunamadı: " + memberRecord.getMemberFullName());
        }
    }

    public Map<String, MemberRecord> getMemberMap() {
        return memberMap;
    }

    public void addBookToMember(MemberRecord memberRecord, long bookId) {
        if (memberRecord.getOwnedBooks().size() > MAX_BOOK_LIMIT) {
            System.out.println("Maks. kitap limitine ulaşılmış");
            return;
        }

        List<Book> books = getBook(bookId);
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
                checkBookInUse(book);
            }
        } else {
            System.out.println("Kitap bulunamadı");
        }
    }

    public void returnToBook(MemberRecord memberRecord, long bookId) {
        List<Book> books = getBook(bookId);
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
            checkBookInUse(book);
        }
    }

    private void checkBookInUse(Book book) {
        for (MemberRecord member : getMemberMap().values()) {
            if (member.getOwnedBooks().contains(book)) {
                System.out.println("Kitap zaten kullanımda: " + member.getMemberFullName());
                return;
            }
        }
    }

    @Override
    public void updateBookName(long bookId, String bookName) {
        List<Book> getBook1 = getBook(bookId);
        try {
            Book getBook11 = getBook1.get(0);
            getBook11.setBookName(bookName);
        } catch (Exception e) {
            System.out.println("Kitap Bulunamadı : " + e.getMessage());
        }
    }

    @Override
    public void updateAuthor(long bookId, String author) {
        List<Book> getBook1 = getBook(bookId);
        try {
            Book getBook11 = getBook1.get(0);
            getBook11.setAuthor(author);
        } catch (Exception e) {
            System.out.println("Kitap Bulunamadı : " + e.getMessage());
        }
    }

    @Override
    public void updatePrice(long bookId, double price) {
        List<Book> getBook1 = getBook(bookId);
        try {
            Book getBook11 = getBook1.get(0);
            getBook11.setPrice(price);
        } catch (Exception e) {
            System.out.println("Kitap Bulunamadı : " + e.getMessage());
        }
    }

    @Override
    public void updateStatus(long bookId, Used used) {
        List<Book> getBook1 = getBook(bookId);
        try {
            Book getBook11 = getBook1.get(0);
            getBook11.setStatus(used);
        } catch (Exception e) {
            System.out.println("Kitap Bulunamadı : " + e.getMessage());
        }
    }

    @Override
    public void updateType(long bookId, TypeOfBook typeOfBook) {
        List<Book> getBook1 = getBook(bookId);
        try {
            Book getBook11 = getBook1.get(0);
            getBook11.setTypeOfBook(typeOfBook);
        } catch (Exception e) {
            System.out.println("Kitap Bulunamadı : " + e.getMessage());
        }
    }

  //  public abstract void createInvoice();

    @Override
    public String toString() {
        return  "Kütüphanenin ismi= " + libraryName + '\n' +
                "Kütüphanenin Adresi= " + libraryAddress + '\n' +
                "Kütüphaneciler= " + librarianMap + '\n' +
                "Kitaplar= " + '\n' + booksMap + '\n';
    }


}

