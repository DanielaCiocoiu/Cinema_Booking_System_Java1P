package Model;

import java.util.Objects;

public class Filme {

    private int idFilm;
    private String numeFilm;
    private String salaNr;
    private int persoanaId;

    public Filme(int idFilm, String numeFilm, String salaNr, int persoanaId) {

        this.idFilm = idFilm;
        this.numeFilm = numeFilm;
        this.salaNr = salaNr;
        this.persoanaId = persoanaId;
    }

    public Filme() {
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getNumeFilm() {
        return numeFilm;
    }

    public void setNumeFilm(String numeFilm) {
        this.numeFilm = numeFilm;
    }

    public int getPersoanaId() {
        return persoanaId;
    }

    public void setPersoanaId(int persoanaId) {
        this.persoanaId = persoanaId;
    }

    public String getSalaNr() {
        return salaNr;
    }

    public void setSalaNr(String salaNr) {
        this.salaNr = salaNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme)) return false;
        Filme filme = (Filme) o;
        return idFilm == filme.idFilm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilm);
    }

    @Override
    public String toString() {
        return "Id rezervare: " + idFilm +
                ", a fost rezervat filmul: " + numeFilm +
                ", in sala: " + salaNr +
                ", pentru persoana cu id: " +  persoanaId;

    }
}
