package controller;

import entities.Book;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {

    public static void InsertBook() {

        BookModel objBookModel = new BookModel();
        String title = JOptionPane.showInputDialog("Insert title: ");
        String year_publication = JOptionPane.showInputDialog("Insert year of publication");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert price of the book"));
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog("Insert ID of the author"));
        Book objBook = new Book();
        objBook.setTitle(title);
        objBook.setYear_publication(year_publication);
        objBook.setPrice(price);
        objBook.setFk_id_autor(idAuthor);

        objBook = (Book) objBookModel.insert(objBook);

        JOptionPane.showMessageDialog(null, objBook.toString());
    }

    public static String showBookAll() {
        BookModel objBookModel = new BookModel();
        String listBooks = "Book List \n";
        for (Object i : objBookModel.findAll()) {
            Book objBook = (Book) i;
            listBooks += objBook.toString();
        }
        JOptionPane.showMessageDialog(null, listBooks);
        return listBooks;
    }

    public static void getBookById() {
        BookModel objBookModel = new BookModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert ID of the book"));
        Book objBook = objBookModel.findById(id);
        JOptionPane.showMessageDialog(null, objBook.toString());

    }

    public static void getBookByTitle() {
        BookModel objBookModel = new BookModel();
        String listBooks = "Books List \n";
        String title = JOptionPane.showInputDialog("Insert title of the book");
        for (Object i : objBookModel.findByName(title)) {

            Book objBook = (Book) i;

            listBooks += objBook.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listBooks);
    }

    public static void getBookByNameAuthor() {
        BookModel objBookModel = new BookModel();
        String listBooks = "Books List \n";
        String name = JOptionPane.showInputDialog("Insert name of the author");
        for (Object i : objBookModel.findByAuthor(name)) {

            Book objBook = (Book) i;

            listBooks += objBook.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listBooks);
    }

    public static void update() {
        BookModel objBookModel = new BookModel();
        String listBooks = showBookAll();
        int idBookUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks + "\n Enter id the book to edit: "));
        Book objBook = objBookModel.findById(idBookUpdate);
        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            String title = JOptionPane.showInputDialog(null, "Enter new title: ", objBook.getTitle());
            String year_publication = JOptionPane.showInputDialog(null, "Enter new year of publication: ", objBook.getYear_publication());
            double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter new price: ", objBook.getPrice()));
            int fk_id_autor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new id author: "), objBook.getFk_id_autor());
            objBook.setTitle(title);
            objBook.setYear_publication(year_publication);
            objBook.setPrice(price);
            objBook.setFk_id_autor(fk_id_autor);
            objBookModel.update(objBook);
        }

    }

    public static void delete() {
        BookModel objBookModel = new BookModel();
        String listBook = showBookAll();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBook + "\n Enter id the book to delete: "));
        Book objBook = objBookModel.findById(idDelete);
        int confirm = 1;
        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "book not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the book? \n" + objBook.toString());
            if (confirm == 0) objBookModel.delete(objBook);
        }
    }

    public static void getBooksByAuthorId() {
        BookModel objBookModel = new BookModel();
        int authorId = Integer.parseInt(JOptionPane.showInputDialog("Insert ID of the author"));

        List<Object> authorBooks = objBookModel.BooksByAuthorId(authorId);

        if (authorBooks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books found for the author with ID " + authorId);
        } else {
            String booksInfo = "Books for Author ID " + authorId + ":\n";
            for (Object obj : authorBooks) {
                Book book = (Book) obj;
                booksInfo += "ID: " + book.getId_book() + "\n";
                booksInfo += "Title: " + book.getTitle() + "\n";
                booksInfo += "Year of Publication: " + book.getYear_publication() + "\n";
                booksInfo += "Price: " + book.getPrice() + "\n";
            }
            JOptionPane.showMessageDialog(null, booksInfo);
        }
    }
}
