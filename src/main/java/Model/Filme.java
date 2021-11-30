package Model;

import java.util.Objects;

public class Filme {

    private int idFilm;
    private String nameFilm;
    private String roomNr;
    private int personId;

    public Filme(int idFilm, String nameFilm, String roomNr, int personId) {

        this.idFilm = idFilm;
        this.nameFilm = nameFilm;
        this.roomNr = roomNr;
        this.personId = personId;
    }

    public Filme() {
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(String roomNr) {
        this.roomNr = roomNr;
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
        return "Id booking: " + idFilm +
                ", was booked film: " + nameFilm +
                ", in room number: " + roomNr +
                ", for person, id: " + personId;

    }
}
