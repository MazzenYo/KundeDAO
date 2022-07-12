package ArtikelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public interface AbstractDAO<T> {


    HashMap<Long, T> getAll();
    void update(T t);

    T delete(T t);
    void create(T t);
    T read(long id);
    T load(ResultSet rs) throws SQLException;
    void getKleinerMinBestand();



}
