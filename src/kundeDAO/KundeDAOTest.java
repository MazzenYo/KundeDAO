package kundeDAO;
public class KundeDAOTest {
    
    public static void main(String[] args) {
    	//Zugriff auf DAO-Objekt bekommen
    	KundeDAO kundeDAO = KundeDAO.getInstance();
    	
        System.out.println("Erzeuge einen Kunden");
        Kunde derKunde = new Kunde(4711l, "Priemer", 1);
        System.out.println("Setze lokale Variable auf NULL und hole Kunden zurück");
        derKunde = null;
        //derKunde = kundeDAO.read(4711l); //So muss der Nutzer KundeDAO kennen (nicht gut!)
        derKunde = Kunde.read(4711l); //So bleibt KundeDAO vor Nutzerklassen verborgen!
        System.out.println("Kunde ist " + derKunde.getKundennummer() + " "
                + derKunde.getName()+", "+derKunde.getKundengruppe());
        System.out.println("Aktualisiere den Kunden. Setze Gruppe auf 2");
        derKunde.setKundengruppe(2);
        derKunde = null;
        derKunde = kundeDAO.read(4711l);
//        derKunde = Kunde.read(4711l);
        System.out.println("Kunde hat jetzt Gruppe " + derKunde.getKundengruppe());

        // Jetzt wird der Kunde gelöscht
        kundeDAO.delete(derKunde); //So muss der Nutzer die Klasse KundeDAO kennen (nicht gut!)
//        derKunde.delete(); //So bleibt KundeDAO vor Nutzerklassen verborgen!
        derKunde = null;
        System.out.println("Versuche den Kunden nach Löschung erneut zu lesen:");
        derKunde = kundeDAO.read(4711l);
        System.out.println(derKunde);

    }
    
}
