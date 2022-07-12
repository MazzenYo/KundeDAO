package ArtikelDAO;


import java.sql.*;

public class TestDAO {
    public static void main(String[] args) {
        AbstractDAO abstractDAO = AbstractDAO.getInstance();
        System.out.println("Erzeuge 3 Artikel");
        Artikel a1 = new Artikel(1, "Artikel1", 9.99f, 5, 1);
        a1.createArtikel();
        Artikel a2 = new Artikel(2, "Artikel2", 1.23f, 6, 5);
        a2.createArtikel();
        Artikel a3 = new Artikel(3, "Artikel3", 1.00f, 1, 5);
        a3.createArtikel();

        a1 = null;
        a1 = Artikel.read(1);
        a1.print();

        Artikel.getKleinerMinBestand();

        a2.setBestand(4);
        Artikel.getKleinerMinBestand();


        a3.delete();
        a3 = null;
        a3 = Artikel.read(3);
        System.out.println("a3="+a3);

        a3 = new Artikel(3, "Artikel3", 1.00f, 1, 5);
        a3.setPreis(2.00f);
        a3.delete();
        a3 = null;





















/*
        //Zugriff auf DAO-Objekt bekommen

       Artikel.getKleinerMinBestand();
        System.out.println("Setze lokale Variable auf NULL und hole Artikel zurück");
        artikel = null;
        artikel = Artikel.read(12);
        artikel.print();





        System.out.println("Aktualisiere den Artikel. Setze Bestand auf 2");
        artikel.setBestand(20);
        artikel = null;
        artikel = Artikel.read(12);
        System.out.println("Artikel hat jetzt Bestand " + artikel.getBestand());

        // Jetzt wird der Artikel gelöscht
        artikel.delete();
        artikel1.delete();
        artikel2.delete();
        artikel = null;
        System.out.println("Versuche den Artikel nach Löschung erneut zu lesen:");
        artikel = abstractDAO.read(12);
        System.out.println(artikel);*/
    }
}
