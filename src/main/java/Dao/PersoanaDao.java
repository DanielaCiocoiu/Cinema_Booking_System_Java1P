package Dao;

import Model.Persoana;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersoanaDao {
    //executa comenzile pe db
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement findAllStatement;
    private PreparedStatement findByNumeStatement;
    private PreparedStatement deleteStatement;

    //ctor care primeste conexiunea
    public PersoanaDao(Connection connection) {
        //primesc conexiunea ca parametru
        this.connection = connection;
        //instantiez statementul
        try {
            createStatement =
                    connection.prepareStatement("INSERT INTO persoana VALUES (null, ?)");

            findAllStatement =
                    connection.prepareStatement("SELECT * FROM persoana ");

            findByNumeStatement =
                    connection.prepareStatement("SELECT * FROM persoana WHERE nume = ?");

            deleteStatement =
                    connection.prepareStatement("DELETE FROM persoana WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }


       // INSERT INTO `company` (`name`, `group`) VALUES (?, ?)
    }

    public boolean create(Persoana p) {
        //indexarea parametrilor (?) incepe de la 1
        try {
            createStatement.setString(1, p.getNume());
            //returneaza un int, nr de inregistrari modificate
         return createStatement.executeUpdate() !=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Persoana> findAll() {
        List<Persoana> persoane = new ArrayList<>();
        try {
            ResultSet rs = findAllStatement.executeQuery();

            while (rs.next()) {
                Persoana p = new Persoana(
                        rs.getInt("id"),
                        rs.getString("nume")
                );
                persoane.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persoane;
    }

    public Optional<Persoana> findByNume(String nume) {
        try {
            findByNumeStatement.setString(1, nume);
            ResultSet rs = findByNumeStatement.executeQuery(); //citesc, fac un read, nu modific baza de date
            if (rs.next()) {
                return Optional.of(new Persoana(
                                rs.getInt("id"),
                                rs.getString("nume")
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

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
