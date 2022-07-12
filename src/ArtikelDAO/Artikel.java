package ArtikelDAO;

public class Artikel {

    private long artikelnummer;
    private String bezeichnung;
    private float preis;
    private int bestand;
    private int mindestbestand;
    private final AbstractDAO abstractDAO = AbstractDAO.getInstance();


    public Artikel(long artnr, String bezeichnung, float preis, int bestand, int mindestbestand) {
        this.artikelnummer = artnr;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.bestand = bestand;
        this.mindestbestand = mindestbestand;
        abstractDAO.setCache(this);
    }

    public static Artikel read(long artikelnummer) {
        return AbstractDAO.getInstance().read(artikelnummer);
    }

    public static void getKleinerMinBestand() {
        AbstractDAO.getInstance().getKleinerMinBestand();
    }

    public void createArtikel() {
        new Artikel(artikelnummer, bezeichnung, preis, bestand, mindestbestand);
        abstractDAO.create(this);
    }

    public long getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(long artikelnummer) {
        this.artikelnummer = artikelnummer;
        abstractDAO.update(this);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
        abstractDAO.update(this);
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
        abstractDAO.update(this);
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
        abstractDAO.update(this);
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public void setMindestbestand(int mindestbestand) {
        this.mindestbestand = mindestbestand;
        abstractDAO.update(this);
    }

    public Artikel delete() {
        return AbstractDAO.getInstance().delete(this);
    }

    public void print() {
        System.out.println("Artikel ist " + getArtikelnummer() + " " + getBezeichnung() + ", " + getPreis() + " " + getBestand() + " " + getMindestbestand() + " ");
    }
}
