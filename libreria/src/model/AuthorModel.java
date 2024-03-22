package model;

import database.ConfigDB;
import entities.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAutor = (Author) obj;
        try {
            String sql = "INSERT INTO autor(name, country) VALUES (?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objAutor.getName());
            objPrepare.setString(2,objAutor.getCountry());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objAutor.setId_author(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Autor insert was successful ");

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objAutor;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    public Author findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;

        try{
            String sql = "SELECT * FROM autor WHERE id_author = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,id);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objAuthor = new Author();
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setCountry(objResult.getString("country"));
                objAuthor.setId_author(objResult.getInt("id_author"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = (Author) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE autor SET name = ?, country = ? WHERE id_author = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2,objAuthor.getCountry());
            objPrepare.setInt(3,objAuthor.getId_author());
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"The update was successful");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Author objAuthor = (Author) obj;
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM autor WHERE id_author = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objAuthor.getId_author());
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "The autor was delete");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.openConnection();
        return false;
    }
}
