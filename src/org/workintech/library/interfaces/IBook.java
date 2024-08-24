package org.workintech.library.interfaces;

import jdk.jshell.Snippet;
import org.workintech.library.enums.TypeOfBook;
import org.workintech.library.enums.Used;

public interface IBook {

    void updateBookName(long bookId, String bookName);

    void updateAuthor(long bookId, String author);

    void updatePrice(long bookId, double price);

    void updateStatus(long bookId, Used used);

    void updateType(long bookId, TypeOfBook typeOfBook);


}
