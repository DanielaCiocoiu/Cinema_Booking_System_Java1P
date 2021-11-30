package Model;

import java.util.Objects;

public class Persoane {
    private int id;
    private String name;
    private String password;

     public Persoane(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Persoane(String name, String password) {
      //  this.id = id;
        this.name = name;
        this.password = password;
    }

    public Persoane() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
        return  " Name " + name;
    }
}
