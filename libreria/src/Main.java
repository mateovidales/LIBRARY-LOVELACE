import controller.AuthorController;
import controller.BookController;
import database.ConfigDB;
import entities.Book;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigDB.openConnection();
                String option;

                do {
                    option = JOptionPane.showInputDialog("""
                            WELCOME TO LIBRARY
                            1. Author
                            2. Book
                            3. salir
                            Choose an option: 
                            """);

                    switch (option) {
                        case "1":
                            String optionAuthor;
                            do {
                                optionAuthor = JOptionPane.showInputDialog("""
                                        MENU AUTHOR
                                        1.Insert Author
                                        2.Find Author
                                        3.Update Author
                                        4.Delete Author
                                        5.Salir
                                        """);

                                switch (optionAuthor) {
                                    case "1":
                                        AuthorController.insertAutor();
                                        break;
                                    case "2":
                                        AuthorController.getAuthorId();
                                        break;
                                    case "3":
                                        AuthorController.updateAuthor();
                                        break;
                                    case "4":
                                        AuthorController.delete();
                                        break;
                                }
                            } while (!optionAuthor.equals("5"));
                            break;

                        case "2":
                            String optionBook;
                            do {
                                optionBook = JOptionPane.showInputDialog("""
                                        MENU BOOK
                                        1.Insert Book
                                        2.Show Books
                                        3.Search Book (Id, title, author)
                                        4.Update Book
                                        5.Delete Book
                                        6.Search books by author
                                        7.exit
                                        """);

                                switch (optionBook) {
                                    case "1":
                                        BookController.InsertBook();
                                        break;
                                    case "2":
                                        BookController.showBookAll();
                                        break;
                                    case "3":
                                        String searchBook;
                                        do {
                                            searchBook = JOptionPane.showInputDialog("""
                                                    SEARCH BOOK
                                                    1.ID
                                                    2.Title
                                                    3.Author
                                                    4.exit
                                                    """);
                                            switch (searchBook) {
                                                case "1":
                                                    BookController.getBookById();
                                                    break;
                                                case "2":
                                                    BookController.getBookByTitle();
                                                    break;
                                                case "3":
                                                    BookController.getBookByNameAuthor();
                                                    break;
                                            }
                                        } while (!searchBook.equals("4"));
                                        break;
                                    case "4":
                                        BookController.update();
                                        break;
                                    case "5":
                                        BookController.delete();
                                        break;
                                    case "6":
                                        BookController.getBooksByAuthorId();
                                        break;
                                }
                            } while (!optionBook.equals("7"));
                            break;
                    }
                } while (!option.equals("3"));
            }
    }
