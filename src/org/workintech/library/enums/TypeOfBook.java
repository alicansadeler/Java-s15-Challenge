package org.workintech.library.enums;

public enum TypeOfBook {
        MEMEOIRS("Anı kitapları", "1"),
    NOVELS("Romanlar", "2"),
    STORY_BOOKS("Hikaye kitapları", "3"),
    TRAVEL_BOOKS("Gezi kitapları","4"),
    POETRY_BOOKS("Şiir kitapları", "5"),
    BIOGRAPHY_BOOKS("Biyografi kitapları", "6"),
    RELIGIOUS_BOOKS("Din kitapları", "7"),
    KID_BOOKS("Çocuk kitapları", "8");

    private final String tr;
    private final String typeCode;


    TypeOfBook(String tr, String typeCode) {
        this.tr = tr;
        this.typeCode = typeCode;
    }


    public String getTr() {
        return tr;
    }

    public String getTypeCode() {
        return typeCode;
    }
}

