package Dao;

import Model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {


    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement findByPersoanaStatement;

    public FilmDao(Connection connection) {
        this.connection = connection;

        try {
            createStatement =
                    connection.prepareStatement("INSERT INTO film VALUES (null, ?, ?)");

            findByPersoanaStatement =
                    connection.prepareStatement("SELECT * FROM film WHERE persoana_id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public boolean create(Film film){
    try {
        createStatement.setString(1, film.getNume());
        createStatement.setInt(2, film.getPersoanaId());

        return createStatement.executeUpdate() != 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public List<Film> findByPersoana(int persoanaId) {
        List<Film> filme = new ArrayList<>();

        try {
            findByPersoanaStatement.setInt(1, persoanaId);

            ResultSet rs = findByPersoanaStatement.executeQuery();

            while (rs.next()) {
                Film f = new Film(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getInt("persoana_id")
                );

                filme.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filme;
    }

}


