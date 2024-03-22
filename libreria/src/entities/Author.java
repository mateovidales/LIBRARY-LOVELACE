package entities;

public class Author {

    private int id_author;
    private String name;
    private String country;

    public Author() {
    }

    public Author(int id, String name, String country) {
        this.id_author = id;
        this.name = name;
        this.country = country;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id_author +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
