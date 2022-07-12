package ArtikelDAO;

public class TestDAO {
    public static void main(String[] args) {

        System.out.println("Erzeuge 3 Artikel");
        Artikel a1 = new Artikel(1, "Artikel1", 9.99f, 5, 1);
        a1.createArtikel();
        Artikel a2 = new Artikel(2, "Artikel2", 1.23f, 6, 5);
        a2.createArtikel();
        Artikel a3 = new Artikel(3, "Artikel3", 1.00f, 1, 5);
        a3.createArtikel();


        a1 = Artikel.read(1);
        a1.print();

        Artikel.getKleinerMinBestand();

        a2.setBestand(4);
        Artikel.getKleinerMinBestand();


        a3 = a3.delete();

        a3 = Artikel.read(3);
        System.out.println("a3=" + a3);

        a3 = new Artikel(3, "Artikel3", 1.00f, 1, 5);
        a3.setPreis(2.00f);
        a3 = a3.delete();
        System.out.println("a3=" + a3);
    }
}
