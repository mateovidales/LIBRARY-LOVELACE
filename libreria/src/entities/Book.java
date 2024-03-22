package entities;

public class Book {
    private int id_book;
    private String title;
    private String year_publication;
    private double price;

    private int fk_id_autor;

    public Book() {
    }

    public Book(int id_book, String title, String year_publication, double price, int fk_id_autor) {
        this.id_book = id_book;
        this.title = title;
        this.year_publication = year_publication;
        this.price = price;
        this.fk_id_autor = fk_id_autor;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(String year_publication) {
        this.year_publication = year_publication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFk_id_autor() {
        return fk_id_autor;
    }

    public void setFk_id_autor(int fk_id_autor) {
        this.fk_id_autor = fk_id_autor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_book=" + id_book +
                ", title='" + title + '\'' +
                ", year_publication='" + year_publication + '\'' +
                ", price=" + price +
                ", fk_id_autor=" + fk_id_autor +
                '}';
    }
}
