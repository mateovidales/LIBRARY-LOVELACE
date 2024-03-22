package controller;

import entities.Author;
import model.AuthorModel;

import javax.swing.*;

public class AuthorController {

    public static void insertAutor(){
        AuthorModel objAutorModel = new AuthorModel();

        String name = JOptionPane.showInputDialog("Insert name: ");
        String country = JOptionPane.showInputDialog("Insert country (Nacionality)");

        Author objAutor = new Author();

        objAutor.setName(name);
        objAutor.setCountry(country);

        objAutor = (Author) objAutorModel.insert(objAutor);

        JOptionPane.showMessageDialog(null, objAutor.toString());
    }
    public static void getAuthorId(){
        AuthorModel objAuthorModel = new AuthorModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert ID of Author: "));
        Author objAuthor = objAuthorModel.findById(id);
        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }

    public static void updateAuthor(){
        AuthorModel objAuthorModel = new AuthorModel();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the id coder to edit"));

        Author objAuthor = objAuthorModel.findById(idUpdate);
        if (objAuthor == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            String name = JOptionPane.showInputDialog(null, "Enter new name",objAuthor.getName());
            String country = JOptionPane.showInputDialog(null, "Enter new country of the author",objAuthor.getCountry());
            objAuthor.setName(name);
            objAuthor.setCountry(country);

            objAuthorModel.update(objAuthor);
        }


    }

    public static void delete(){
        AuthorModel objAuthorModel = new AuthorModel();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("Enteder id the author to delete: "));
        Author objAuthor = objAuthorModel.findById(idDelete);
        int confirm = 1;
        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author was not found");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete the author? \n"+objAuthor.toString());
            if(confirm == 0) objAuthorModel.delete(objAuthor);
        }
    }
}
