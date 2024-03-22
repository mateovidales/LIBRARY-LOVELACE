package model;

import database.ConfigDB;
import entities.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD{
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) obj;
        try {
            String sql = "INSERT INTO libro(title, year_publication,price, fk_id_autor) VALUES (?,?,?,?);";
            PreparedStatement objPreparedd = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPreparedd.setString(1,objBook.getTitle());
            objPreparedd.setString(2,objBook.getYear_publication());
            objPreparedd.setDouble(3,objBook.getPrice());
            objPreparedd.setInt(4,objBook.getFk_id_autor());
            objPreparedd.execute();
            ResultSet objResult = objPreparedd.getGeneratedKeys();
            while (objResult.next()){
                objBook.setId_book(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Book insert was successfull");
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objBook;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listBooks = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM libro";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                Book objBook = new Book();
                objBook.setId_book(objResult.getInt("id_book"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYear_publication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getDouble(("price")));
                objBook.setFk_id_autor(objResult.getInt("fk_id_autor"));
                listBooks.add(objBook);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return listBooks;
    }

    public Book findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;
        try {
            String sql = "SELECT * FROM libro WHERE id_book = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,id);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objBook = new Book();
                objBook.setTitle(objResult.getString("title"));
                objBook.setYear_publication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getDouble(("price")));
                objBook.setFk_id_autor(objResult.getInt("fk_id_autor"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objBook;
    }
    public List<Object> findByName(String title) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listBooks = new ArrayList<>();
        Book objBook = null;
        try {
            String sql = "SELECT * FROM libro WHERE title like ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1,"%"+title+"%");
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objBook = new Book();
                objBook.setId_book(objResult.getInt("id_book"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYear_publication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getDouble(("price")));
                objBook.setFk_id_autor(objResult.getInt("fk_id_autor"));
                listBooks.add(objBook);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listBooks;
    }

    public List<Object> findByAuthor(String name){
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listBooks = new ArrayList<>();
        Book objBook = null;
        try {
            String sql = "SELECT * FROM libro JOIN autor ON libro.fk_id_autor = autor.id_author WHERE autor.name like ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1,name);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objBook = new Book();
                objBook.setId_book(objResult.getInt("id_book"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYear_publication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getDouble(("price")));
                objBook.setFk_id_autor(objResult.getInt("fk_id_autor"));
                listBooks.add(objBook);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listBooks;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) obj;
        boolean BookUpdate = false;
        try {
            String sql = "UPDATE libro SET title = ?, year_publication = ?, price = ?, fk_id_autor = ? WHERE id_book = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setString(2,objBook.getYear_publication());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getFk_id_autor());

            int affected = objPrepare.executeUpdate();
            if (affected>0){
                BookUpdate = true;
                JOptionPane.showMessageDialog(null,"The update was successful" );
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return BookUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Book objBook = (Book) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean bookDelete = false;
        try {
            String sql = "DELETE FROM libro WHERE id_book = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,objBook.getId_book());
            int affectedRows = objPrepared.executeUpdate();
            if (affectedRows > 0){
                bookDelete = true;
                JOptionPane.showMessageDialog(null, "The delete was successfull");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return false;
    }

    public List<Object> BooksByAuthorId(int id) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listBooks = new ArrayList<>();

        try {
            String sql = "SELECT * FROM libro WHERE fk_id_autor = ?";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                Book objBook = new Book();
                objBook.setId_book(objResult.getInt("id_book"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYear_publication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setFk_id_autor(objResult.getInt("fk_id_autor"));
                listBooks.add(objBook);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listBooks;
    }

}
