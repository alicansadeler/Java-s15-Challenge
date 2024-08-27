package org.workintech.library.management;
import org.workintech.library.Book;
import org.workintech.library.member.MemberRecord;
import java.util.HashMap;
import java.util.Map;

public class MemberManagement {
    public Map<String, MemberRecord> memberMap;

    public MemberManagement() {
        this.memberMap =new HashMap<>();
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


    public void checkBookInUse(Book book) {
        for (MemberRecord member : getMemberMap().values()) {
            if (member.getOwnedBooks().contains(book)) {
                System.out.println("Kitap zaten kullanımda: " + member.getMemberFullName());
                return;
            }
        }
    }

}
