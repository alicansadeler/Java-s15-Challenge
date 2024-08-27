package org.workintech.library.management;

import org.workintech.library.Book;
import org.workintech.library.enums.TypeOfBook;
import org.workintech.library.enums.Used;
import org.workintech.library.interfaces.IBook;

import java.util.*;
import java.util.stream.Collectors;

public class BookManagement implements IBook {

    public Map<Long, Book> booksMap;

    public BookManagement() {
        this.booksMap = new HashMap<>();
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
}
