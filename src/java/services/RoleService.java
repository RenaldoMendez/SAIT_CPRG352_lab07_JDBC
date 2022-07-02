/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.sql.SQLException;
import java.util.List;
import models.Role;
import models.User;

/**
 * class for viewing all the roles
 * @author Renaldo
 */
public class RoleService {
    
     public List<Role> getAll() throws SQLException{
        RoleDB roleDB = new RoleDB();
        
        //returns a list object containing a list of role objects
        List<Role> roles = roleDB.getAllRoles(); 
        
        //returns the list containing all roles
        return roles; 
    }
    
    //implementing the other methods are helpfiul
    
  
}
