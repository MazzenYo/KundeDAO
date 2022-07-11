package kundeDAO;

public class Kunde {
    
    private long kundennummer;
    private String name;
    private int kundengruppe;
    //für Zugriff KundeDAO
    private KundeDAO kundeDAO = KundeDAO.getInstance();
    
    public Kunde(long kdnr, String nme, int kndngrpp) {
        this.kundennummer = kdnr;
        this.name = nme;
        this.kundengruppe = kndngrpp;
        kundeDAO.create(this);
    }

    public long getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(long kundennummer) {
        this.kundennummer = kundennummer;
        kundeDAO.update(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        kundeDAO.update(this);
    }

    public int getKundengruppe() {
        return kundengruppe;
    }

    public void setKundengruppe(int kundengruppe) {
        this.kundengruppe = kundengruppe;
        kundeDAO.update(this);
    }
    
    /** Löschen eines vorhandenen Kunden als Objekt und in der
     * Datenhaltungsschicht
     */
    public void delete() {
    	KundeDAO.getInstance().delete(this);
    }
	
	/** Holen eines vorhandenen Kunden aus der Datenhaltungsschicht
	*   über die Kundennummer
	**/
	public static Kunde read(long kundennummer) {
		return KundeDAO.getInstance().read(kundennummer);
	}
}
