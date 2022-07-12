package ArtikelDAO;

import java.sql.*;
import java.util.HashMap;

import static java.lang.System.out;

public class AbstractDAO {

    private final static String insertStatementString = "INSERT INTO ARTIKEL VALUES(?,?,?,?,?)";
    private final static String findStatementString = "SELECT Artikelnr, Bezeichnung, Preis,Bestand,Mindestbestand FROM Artikel WHERE Artikelnr=?";
    private final static String updateStatementString = "UPDATE Artikel SET Bezeichnung = ?, Preis = ?, Bestand = ?, Mindestbestand = ?  WHERE Artikelnr = ?";
    private final static String deleteStatementString = "DELETE FROM Artikel WHERE Artikelnr = ?";
    private final static String findKleinerMinBestand = "SELECT Artikelnr, Bezeichnung, Preis,Bestand,Mindestbestand FROM Artikel WHERE Bestand < Mindestbestand";
    private static final AbstractDAO instance = new AbstractDAO();
    private final HashMap<Long, Artikel> cache = new HashMap<>();
    private Connection db;
    private ResultSet rs;

    private AbstractDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:xdb");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AbstractDAO getInstance() {
        return instance;
    }

    public void setCache(Artikel art) {
        cache.put(art.getArtikelnummer(), art);
    }

    public void create(Artikel art) {
        PreparedStatement insertStatement;
        try {
            insertStatement = db.prepareStatement(insertStatementString);
            insertStatement.setLong(1, art.getArtikelnummer());
            insertStatement.setString(2, art.getBezeichnung());
            insertStatement.setFloat(3, art.getPreis());
            insertStatement.setFloat(4, art.getBestand());
            insertStatement.setFloat(5, art.getMindestbestand());
            insertStatement.execute();
            setCache(art);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Artikel read(long artikelnummer) {
        Artikel result = cache.get(artikelnummer);
        // Zunaechst in der Registry suchen
        if (result != null) {
            return result;
        }

        /* Der Kunde ist noch nicht im Speicher, laden aus der DB */
        PreparedStatement findStatement;
        rs = null;
        try {
            findStatement = db.prepareStatement(findStatementString);
            findStatement.setLong(1, artikelnummer);
            rs = findStatement.executeQuery();
            if (!rs.next()) return null;
            result = load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Artikel load(ResultSet rs) throws SQLException {
        long artnr = rs.getLong(1);
        String bez = rs.getString(2);
        float preis = rs.getFloat(3);
        int bestand = rs.getInt(4);
        int minBestand = rs.getInt(5);

        Artikel result = new Artikel(artnr, bez, preis, bestand, minBestand);
        setCache(result);
        return result;
    }

    public void update(Artikel artikel) {
        PreparedStatement updateStatement;
        try {
            updateStatement = db.prepareStatement(updateStatementString);
            updateStatement.setLong(5, artikel.getArtikelnummer());
            updateStatement.setString(1, artikel.getBezeichnung());
            updateStatement.setFloat(2, artikel.getPreis());
            updateStatement.setInt(3, artikel.getBestand());
            updateStatement.setInt(4, artikel.getMindestbestand());
            updateStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Artikel delete(Artikel artikel) {
        PreparedStatement deleteStatement;
        try {
            deleteStatement = db.prepareStatement(deleteStatementString);
            deleteStatement.setLong(1, artikel.getArtikelnummer());
            deleteStatement.execute();
            cache.remove(artikel.getArtikelnummer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getKleinerMinBestand() {
        PreparedStatement findStatement;
        rs = null;
        try {
            findStatement = db.prepareStatement(findKleinerMinBestand);
            rs = findStatement.executeQuery();
            out.println("Artikel unter Mindestbestand sind:");
            while (rs.next()) {
                out.print(rs.getLong(1) + " ");
                out.print(rs.getString(2) + " ");
                out.print(rs.getFloat(3) + "€ ");
                out.print(rs.getInt(4) + " ");
                out.print(rs.getInt(5));
                out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
