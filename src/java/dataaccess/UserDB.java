
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 * This class contains the details for CRUD functions pertaining to the user table
 *  It directly accesses and passes SQL commands to the Database 
 * CRUD operations: view all users, add a user, update a user, and delete a user
 * @author Renaldo
 */
public class UserDB {
    
    /**
     * returns an array list of every user that is in the database
     * precondition: there is at least one user record in the user table
     * postcondition: an array list containing all the users in the user table is returned
     * @return ArrayList<User> the ArrayList to return
     */
    public List<User> viewAllUsers() throws SQLException{
        //create List that holds user objects
        List<User> users = new ArrayList<User>();
        //create a connection object to the database from the connection pool
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        //declare a PreparedStatement variable to hold a prepared statemene
        PreparedStatement ps = null;
        //create an object to hold the results of the DB query
        ResultSet rs = null;
        
        //sql statement to retrieve the info from the DB
        String sqlStatement = "SELECT * FROM user";
        
        try{
            ps = con.prepareStatement(sqlStatement);
            rs = ps.executeQuery();
            while(rs.next()){
                //retrieve desired field in
                String email = rs.getString(1);
                boolean accountStatus = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String role = rs.getString(6);
                
                //create a user object to hold the retrieved information
                User user = new User(email, accountStatus, firstName, lastName, role);
                //add user object to ArrayList users
                users.add(user);
            }
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);   
        }
        return users;        
    }
    /**
     * retrieves a user from the database with the specified email 
     * precondition: user record must exist in the user table
     * postcondition: user object is returned containing appropriate information from the user table
     * @param email the email of the user
     * @return user the user object to requested
     */
    public User getUser(String email) throws SQLException{
        //user object that will hold the requested information
        User user = null;
        //create a connection object to the database from the connection pool
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        //declare a PreparedStatement variable to hold a prepared statemene
        PreparedStatement ps = null;
        //create an object to hold the results of the DB query
        ResultSet rs = null;
        
        //sql statement to retrieve the info from the DB
        String sqlStatement = "SELECT * FROM user WHERE email = ?";
        
        try{
            ps = con.prepareStatement(sqlStatement);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()){
                //retrieve desired fields from the result set
                String retrievedEmail = rs.getString(1);
                boolean accountStatus = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String role = rs.getString(6);
                
                //create a user object to hold the retrieved information
                 user = new User(email, accountStatus, firstName, lastName, role);
                //add user object to ArrayList users
            }
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);   
        }
        return user; 
    }
    
    /**
     * Add a user 
     * precondition: N/A
     * postcondition: A user object is created and added to the user table
     * @param newUser the user to add
     */
    public void addUser(User newUser) throws SQLException{
        //create/grab a connection object from the connection pool to connect to the database
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        //declare a variable as a PreparedStatement object
        PreparedStatement ps = null;
        
        //the sql statement to retrive the desired data from the database
        String sqlStatement = "INSERT INTO user (email, active, first_name, last_name, password, role) VALUES(?,?,?,?,?,?)";
        
        try{
            //pass the sql statement into the prepared statement method
            ps = con.prepareStatement(sqlStatement);
            ps.setString(1, newUser.getEmail());
            ps.setBoolean(2, newUser.isIsActive());
            ps.setString(3, newUser.getFirstName());
            ps.setString(4, newUser.getLastName());
            ps.setString(5, newUser.getPassword());
            ps.setString(6, newUser.getRole());
            ps.executeUpdate();
        }finally{
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
            //not retrieving anything from the DB so no need for result set
        }
    }
    
    /**
     * Updates an existing user object
     * precondition: the user to be updated is stored in the user table.
     * postcondition: the user passed as a parameter to be updated has been updated
     * @param userToUpdate the user to update
     */
    public void updateUser(User userToUpdate) throws SQLException{
        //create/grab connection object from the connection pool to conenct to the database
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        //sql statement that will update the user table with the informtion provided
        String sqlStatement = "UPDATE user SET active=?, first_name=?, last_name=?, password=?, role=? WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sqlStatement);
            ps.setBoolean(1, userToUpdate.isIsActive());
            ps.setString(2, userToUpdate.getFirstName());
            ps.setString(4, userToUpdate.getLastName());
            ps.setString(5, userToUpdate.getPassword());
            ps.setString(6, userToUpdate.getRole());
            ps.setString(7, userToUpdate.getEmail());
            ps.executeUpdate();
        }finally{
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    /**
     * Deletes an existing user
     * precondition: the user table is not empty; contains a valid user record
     * postcondition: the user passed a parameter is no longer in the user table
     * @param userToDelete the user to delete
     */
    public void deleteUser(User userToDelete) throws SQLException{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sqlStatement = "DELETE FROM user WHERE email=?";
        
        try{
            ps = con.prepareStatement(sqlStatement);
            ps.setString(1, userToDelete.getEmail());
            ps.executeUpdate();
        }finally{
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
