package org.workintech.library;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Librarian {
    private  long librarianId;
    private  String librarianFullName;
    private  String librarianPassword;


    public Librarian(long librarianId, String librarianFullName, String librarianPassword) {
        if (isValidLibrarian(librarianFullName, librarianPassword)) {
            this.librarianId = librarianId;
            this.librarianFullName = librarianFullName;
            this.librarianPassword = librarianPassword;

        } else {
            throw new IllegalArgumentException("Invalid librarian details provided!");
        }
    }

    public long getLibrarianId() {
        return librarianId;
    }

    private boolean isValidLibrarian(String librarianFullName, String librarianPassword) {
        return  librarianFullName != null && librarianFullName.length() > 4 &&
                librarianPassword != null && librarianPassword.length() > 10;
    }

    public void setLibrarianId(long librarianId) {
        this.librarianId = librarianId;
    }

    public String getLibrarianFullName() {
        return librarianFullName;
    }

    public void setLibrarianFullName(String librarianFullName) {
        this.librarianFullName = librarianFullName;
    }

    public String getLibrarianPassword() {
        return librarianPassword;
    }

    public void setLibrarianPassword(String librarianPassword) {
        this.librarianPassword = librarianPassword;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Librarian librarian = (Librarian) object;
        return librarianId == librarian.librarianId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(librarianId);
    }

    @Override
    public String toString() {
        return  '\n' +
                "Librarian Id=" + librarianId + '\n' +
                "Librarian Full Name= " + librarianFullName + '\n' +
                "Librarian Password= " + librarianPassword + '\n' +
                '}';
    }
}
