package ArtikelDAO;

public class Artikel {

    private final ArtikelDAO artikelDAO = ArtikelDAO.getInstance();
    private long artikelnummer;
    private String bezeichnung;
    private float preis;
    private int bestand;
    private int mindestbestand;


    public Artikel(long artnr, String bezeichnung, float preis, int bestand, int mindestbestand) {
        this.artikelnummer = artnr;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.bestand = bestand;
        this.mindestbestand = mindestbestand;
        artikelDAO.setCache(this);
    }

    public static Artikel read(long artikelnummer) {
        return ArtikelDAO.getInstance().read(artikelnummer);
    }

    public static void getKleinerMinBestand() {
        ArtikelDAO.getInstance().getKleinerMinBestand();
    }

    public void createArtikel() {
        new Artikel(artikelnummer, bezeichnung, preis, bestand, mindestbestand);
        artikelDAO.create(this);
    }

    public long getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(long artikelnummer) {
        this.artikelnummer = artikelnummer;
        artikelDAO.update(this);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
        artikelDAO.update(this);
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
        artikelDAO.update(this);
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
        artikelDAO.update(this);
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public void setMindestbestand(int mindestbestand) {
        this.mindestbestand = mindestbestand;
        artikelDAO.update(this);
    }

    public Artikel delete() {
        return ArtikelDAO.getInstance().delete(this);
    }

    public void print() {
        System.out.println("Artikel ist " + getArtikelnummer() + " " + getBezeichnung() + ", " + getPreis() + " " + getBestand() + " " + getMindestbestand() + " ");
    }
}
