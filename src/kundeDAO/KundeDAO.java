package kundeDAO;

import java.util.*;
import java.sql.*;

public class KundeDAO {

    /* Wir benoetigen eine Struktur, um uns alle schon im Speicher
     * vorhandenen Kunden zu merken. 
     */
    private final HashMap<Long, Kunde> cache = new HashMap<Long, Kunde>();
    private Connection db;
    private ResultSet rs;
    
    private static final KundeDAO instance = new KundeDAO();
    
    private KundeDAO() {
        try {

            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:xdb");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    
    /* KundeDAO ist als Singleton implementiert */
	public static KundeDAO getInstance() {
    	return instance;
    }
    
    /* Es folgen die so genannten CRUD-Methoden:
     * (C)reate
     * (R)ead
     * (U)pdate
     * (D)elete
     */
    private final static String insertStatementString =
            "INSERT INTO KUNDEN VALUES(?, ?, ?)";

    /** Methode zum Einf�gen von Kunden in die DB
    Eigentlich wird der Schl�ssel Kundennummer erst hier
    durch die Datenbank bestimmt. Deshalb wird auch Long
    zur�ckgeliefert. Dann wird noch ein entsprechendes Muster
    ben�tigt, das einen aktuellen ID zur�ckliefert.
    Wir haben an dieser Stelle darauf verzichtet und gehen
    davon aus, dass ein eindeutiger ID bei der Anlage des Kunden
    mitgegeben wird. */
    public long create(Kunde kd) {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = db.prepareStatement(insertStatementString);
            insertStatement.setLong(1, kd.getKundennummer());
            insertStatement.setString(2, kd.getName());
            insertStatement.setInt(3, kd.getKundengruppe());
            insertStatement.execute();
            cache.put(kd.getKundennummer(), kd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kd.getKundennummer();
    }

    private final static String findStatementString =
            "SELECT KDNR, NAME, KDGRP FROM KUNDEN WHERE KDNR=?";

    /** Methode zum Finden eines Kunden anhand der Kundennummer 
     * im Speicher oder aus der Datenbank
     */
    public Kunde read(long kdnr) {
        Kunde result = cache.get(kdnr);
        // Zunaechst in der Registry suchen
        if (result != null) {
            return result;
        }

        /* Der Kunde ist noch nicht im Speicher, laden aus der DB */
        PreparedStatement findStatement = null;
        rs = null;
        try {
            findStatement = db.prepareStatement(findStatementString);
            findStatement.setLong(1, kdnr);
            rs = findStatement.executeQuery();
            if (!rs.next()) return null;
            result = load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** Methode zum Laden eines Kunden in den Speicher aus dem Resultset */
    private Kunde load(ResultSet rs) throws SQLException {
        long kdnr = rs.getLong(1);
        String name = rs.getString(2);
        int kundengruppe = rs.getInt(3);
        Kunde result = new Kunde(kdnr, name, kundengruppe);
        cache.put(kdnr, result);
        return result;
    }
    private final static String updateStatementString =
            "UPDATE KUNDEN SET NAME = ?, KDGRP = ? WHERE KDNR = ?";

    /** Methode zum Aktualisieren eines Kunden in der DB */
    public void update(Kunde kd) {
        PreparedStatement updateStatement = null;
        try {
            updateStatement = db.prepareStatement(updateStatementString);
            updateStatement.setLong(3, kd.getKundennummer());
            updateStatement.setString(1, kd.getName());
            updateStatement.setInt(2, kd.getKundengruppe());
            updateStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final static String deleteStatementString =
            "DELETE FROM KUNDEN WHERE KDNR = ?";

    public void delete(Kunde kd) {
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = db.prepareStatement(deleteStatementString);
            deleteStatement.setLong(1, kd.getKundennummer());
            deleteStatement.execute();
            cache.remove(kd.getKundennummer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
