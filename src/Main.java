import org.workintech.library.Book;
import org.workintech.library.Librarian;
import org.workintech.library.Library;
import org.workintech.library.enums.Discount;
import org.workintech.library.enums.TypeOfBook;
import org.workintech.library.enums.Used;
import org.workintech.library.member.MemberOfficer;
import org.workintech.library.member.MemberRecord;
import org.workintech.library.member.MemberStudent;

public class Main {
    public static void main(String[] args) {
// kullanıcılar
        MemberRecord student1 = new MemberStudent("12345678911", "Ali Can Sadeler", "Yenimahalle/ankara",
                "05075150531", "18 temmuz", Discount.STUDENT, "123456789", "Mühendislik Fakültesi");

        MemberOfficer officer1 = new MemberOfficer("12345678913", "Recep Sadeler", "Yakutiye/Erzurum",
                "05075150531", "1 haziran", Discount.OFFICER, "Atatürk Üni.", "Prof.");

// kütüphaneciler
        Librarian librarian1 = new Librarian(1, "Ahmet Yılmaz", "kutuphaneci123");


// kütüphaneler
        Library library1 = new Library("Milli Kütüphane", "Bahçelievler Mahallesi, Ankara, Türkiye", librarian1);



// kitaplar
        library1.bookManagement.addBook(
                new Book(1, "Tutunamayanlar", "Oğuz Atay", 45.99, Used.IN_USE, TypeOfBook.NOVELS),
                new Book(2, "İnce Memed", "Yaşar Kemal", 35.50, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(4, "Çalıkuşu", "Reşat Nuri Güntekin", 30.75, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(5, "Yaban", "Yakup Kadri Karaosmanoğlu", 28.50, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(6, "Şu Çılgın Türkler", "Turgut Özakman", 50.00, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(7, "Kürk Mantolu Madonna", "Sabahattin Ali", 25.99, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(3, "Saatleri Ayarlama Enstitüsü", "Ahmet Hamdi Tanpınar", 40.00, Used.IN_USE, TypeOfBook.NOVELS),
                new Book(13, "Otostopçunun Galaksi Rehberi", "Douglas Adams", 38.50, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(17, "İstanbul Hatırası", "Ahmet Ümit", 42.50, Used.NOT_USED, TypeOfBook.NOVELS),
                new Book(19, "Yalnızız", "Peyami Safa", 33.75, Used.NOT_USED, TypeOfBook.NOVELS),

                new Book(8, "Anılar", "Halide Edip Adıvar", 35.00, Used.NOT_USED, TypeOfBook.MEMEOIRS),
                new Book(9, "Nutuk", "Mustafa Kemal Atatürk", 60.00, Used.NOT_USED, TypeOfBook.MEMEOIRS),
                new Book(10, "Mor Salkımlı Ev", "Halide Edip Adıvar", 32.50, Used.NOT_USED, TypeOfBook.MEMEOIRS),
                new Book(18, "Osmanlı'da Harem", "İlber Ortaylı", 48.00, Used.NOT_USED, TypeOfBook.MEMEOIRS),

                new Book(11, "Şiirler", "Orhan Veli Kanık", 22.99, Used.NOT_USED, TypeOfBook.POETRY_BOOKS),
                new Book(12, "Bütün Şiirleri", "Nazım Hikmet", 45.00, Used.NOT_USED, TypeOfBook.POETRY_BOOKS),

                new Book(14, "Küçük Prens", "Antoine de Saint-Exupéry", 20.00, Used.NOT_USED, TypeOfBook.KID_BOOKS),

                new Book(15, "Mesnevi", "Mevlana", 55.00, Used.NOT_USED, TypeOfBook.RELIGIOUS_BOOKS),

                new Book(16, "Dede Korkut Hikayeleri", "Anonim", 30.00, Used.NOT_USED, TypeOfBook.STORY_BOOKS)


        );

          System.out.println(library1.bookManagement.getAllBooks().size());
          System.out.println((library1.bookManagement.getBookGenre(TypeOfBook.KID_BOOKS)));
          System.out.println(library1.bookManagement.getBook(2));
          System.out.println(library1.bookManagement.getBook("tutunamayanlar"));
          System.out.println(library1.bookManagement.getBook("şiirler"));



        library1.memberManagement.addMember(student1);
        library1.memberManagement.addMember(officer1);
        System.out.println(library1.memberManagement.getMemberMap());

        System.out.println("******************");

        library1.addBookToMember(student1, 2);
      library1.addBookToMember(officer1, 2);
      library1.returnToBook(student1, 2);


    }

}