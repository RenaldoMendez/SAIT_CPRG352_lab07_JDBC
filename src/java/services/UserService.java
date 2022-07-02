/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.sql.SQLException;
import java.util.List;
import models.User;

/**
 * This class will be called by the UserServlet
 * all crud functionality
 * @author Renaldo
 */
public class UserService {
    
    public List<User> getAll() throws SQLException{
        UserDB userDB = new UserDB();
        
        List<User> users = userDB.viewAllUsers(); //returns a list of user objects
        return users; //returns the list containing the users
    }
    
     /**
      * retrieve user information for the specific email provided
      * @param email the email of the requested user
      * @return user, the user object to return
      * @throws SQLException 
      */
     public User getUser(String email) throws SQLException{
         UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
     }
             
     /**
      *  Adds a user to the database
      * @param email
      * @param status
      * @param firstName
      * @param lastName
      * @param password
      * @param role
      * @throws SQLException 
      */
     public void insert(String email, boolean status, String firstName, String lastName, String password, String role) throws SQLException{
         User user = new User(email, status, firstName, lastName, password, role);
         UserDB userDB = new UserDB();
         userDB.addUser(user);
     }
     
     /**
      * Update an existing user
      * @param status the status of the user account
      * @param firstName the first name of the user
      * @param lastName
      * @param password
      * @param role
      * @throws SQLException 
      */
     public void update(boolean status, String firstName, String lastName, String password, String role) throws SQLException{
         User user = new User(status, firstName, lastName, password, role);
         UserDB userDB = new UserDB();
         userDB.updateUser(user); 
     }
     
     /**
      * Delete an existing user
      * @param email
      * @throws SQLException 
      */
     public void delete(String email) throws SQLException{
         UserDB userDB = new UserDB();
         User userToDelete = userDB.getUser(email); //gets user object
         userDB.deleteUser(userToDelete); //passes user object to delete
     }
}
