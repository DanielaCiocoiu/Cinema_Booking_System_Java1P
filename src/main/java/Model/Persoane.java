package Model;

import java.util.Objects;

public class Persoane {
    private int id;
    private String nume;
    private String password;

    public Persoane() {
    }

    public Persoane(int id, String nume, String password) {
        this.id = id;
        this.nume = nume;
        this.password = password;
    }

    public Persoane(String nume, String password) {
        this.id = id;
        this.nume = nume;
        this.password = password;
    }

    public Persoane(String nume) {
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persoane)) return false;
        Persoane persoane = (Persoane) o;
        return id == persoane.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  " Nume " + nume;
    }
}
