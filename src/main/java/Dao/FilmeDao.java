package Dao;

import Model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao {

    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement findByPersoanaStatement;

    public FilmeDao(Connection connection) {
        this.connection = connection;

        try {
            createStatement =
                    connection.prepareStatement("INSERT INTO filme VALUES (?, ?, ?, ?)");
            findByPersoanaStatement =
                    connection.prepareStatement("SELECT * FROM filme WHERE persoana_id = ?");
            deleteStatement =
                    connection.prepareStatement("DELETE FROM filme WHERE id_film = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public boolean create(Filme filme){
    try {
        createStatement.setInt(1, filme.getIdFilm());
        createStatement.setString(2, filme.getNameFilm());
        createStatement.setString(3, filme.getRoomNr());
        createStatement.setInt(4, filme.getPersonId());

        return createStatement.executeUpdate() != 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public List<Filme> findByPersoana(int persoanaId) {
        List<Filme> filme = new ArrayList<>();

        try {
            findByPersoanaStatement.setInt(1, persoanaId);

            ResultSet rs = findByPersoanaStatement.executeQuery();

            while (rs.next()) {
                Filme f = new Filme(
                        rs.getInt("id_film"),
                        rs.getString("nume_film"),
                        rs.getString("sala_nr"),
                        rs.getInt("persoana_id")
                );

                filme.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }

    public boolean delete(int idFilm) {
        try {
            deleteStatement.setInt(1, idFilm);
            return deleteStatement.executeUpdate() != 0;// fac scrieri in baza de date
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}


