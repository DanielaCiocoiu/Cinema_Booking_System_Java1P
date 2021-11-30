package Dao;

import Model.Persoane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersoaneDao {
    //execut comenzile pe db
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement findAllStatement;
    private PreparedStatement findByNumeStatement;
    private PreparedStatement deleteStatement;

    public PersoaneDao(Connection connection) {
        //primesc conexiunea ca parametru
        this.connection = connection;
        //instantiez statementul
        try {
            createStatement =
                    connection.prepareStatement("INSERT INTO persoane VALUES (null, ?,?) ");

            findAllStatement =
                    connection.prepareStatement("SELECT * FROM persoane ");

            findByNumeStatement =
                    connection.prepareStatement("SELECT * FROM persoane WHERE person_name = ? and password = ? ");
            deleteStatement =
                    connection.prepareStatement("DELETE FROM persoane WHERE id_pers = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Persoane p) {
        //indexarea parametrilor (?) incepe de la 1
        try {
            createStatement.setString(1, p.getName());
            createStatement.setString(2, p.getPassword());
            //returneaza un int, nr de inregistrari modificate
         return createStatement.executeUpdate() !=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Persoane> findAll() {
        List<Persoane> persoane = new ArrayList<>();
        try {
            ResultSet rs = findAllStatement.executeQuery();

            while (rs.next()) {
                Persoane p = new Persoane(
                        rs.getInt("id_pers"),
                        rs.getString("person_name"),
                        rs.getString("password")
                );
                persoane.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persoane;
    }

    public Optional<Persoane> findByNume(Persoane persoane) {
        try {
            findByNumeStatement.setString(1, persoane.getName());
            findByNumeStatement.setString(2, persoane.getPassword());
            ResultSet rs = findByNumeStatement.executeQuery(); //citesc, fac un read, nu modific baza de date
            if (rs.next()) {
                return Optional.of(new Persoane(
                                rs.getString("person_name"),
                                rs.getString("password")
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
/*public Optional<Persoane> findByName(String username) {
    try {
        findByNumeStatement.setString(1, username);

        ResultSet rs = findByNumeStatement.executeQuery(); //citesc, fac un read, nu modific baza de date
        if (rs.next()) {
            return Optional.of(new Persoane(
                            rs.getInt("id"),
                            rs.getString("person_name"),
                            rs.getString("password")
                    )
            );
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return Optional.empty();
}*/

    public boolean delete(int id) {
        try {
            deleteStatement.setInt(1, id);
            return deleteStatement.executeUpdate() != 0;// fac scrieri in baza de date
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
