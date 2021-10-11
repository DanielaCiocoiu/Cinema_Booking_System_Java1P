package Model;

import java.util.Objects;

public class Film {

    private int id;
    private String nume;
    private int persoanaId;

    public Film(int id, String nume, int persoanaId) {
        this.id = id;
        this.nume = nume;
        this.persoanaId = persoanaId;
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

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPersoanaId() {
        return persoanaId;
    }

    public void setPersoanaId(int persoanaId) {
        this.persoanaId = persoanaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "film{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}
