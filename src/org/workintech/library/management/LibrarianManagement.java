package org.workintech.library.management;

import org.workintech.library.Librarian;

import java.util.HashMap;
import java.util.Map;

public class LibrarianManagement {
    public Map<Long, Librarian> librarianMap;

    public LibrarianManagement() {
        this.librarianMap = new HashMap<>();
    }

    public void registeredLibrarians(Librarian librarian) {
        if(librarianMap.containsKey(librarian.getLibrarianId())) {
            throw new IllegalArgumentException("Kütüphaneci sisteme önceden kaydedilmiş.");
        } else {
            librarianMap.put(librarian.getLibrarianId(), librarian);
        }
    }

    public  Map<Long, Librarian> getLibrarians() {
        return librarianMap;
    }
}
