
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

/**
 * This class contains the details for CRUD functions pertaining to the role table;
 * it directly accesses and passes SQL commands to the Database 
 * 
 * CRUD operations: view all roles, add a role, update a role, and delete a role
 * 
 * Only necessary to implement the method to retrieve all roles for this labe, but
 *      you can also implement all the CRUD methods; they are helpful on future projects.
 * @author Renaldo
 */
public class RoleDB {
    
    /**
     * Returns an array list containing all the roles in the database
     * precondition: ArrayList must not be empty; contain at least one role object
     * postcondition: an array list containing all the roles in the database is returned
     * @return ArrayList<Role> the ArrayList to return
     */
    public List<Role> getAllRoles() throws SQLException{
        //create List that holds user objects
        List<Role> roles = new ArrayList<Role>();
        //create a connection object to the database from the connection pool
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        //declare a PreparedStatement variable to hold a prepared statemene
        PreparedStatement ps = null;
        //create an object to hold the results of the DB query
        ResultSet rs = null;
        
        //sql statement to retrieve the info from the DB
        String sqlStatement = "SELECT * FROM role";
        
        try{
            ps = con.prepareStatement(sqlStatement);
            rs = ps.executeQuery();
            while(rs.next()){
                //retrieve desired field in
                int roleID = rs.getInt(1);
                String roleName = rs.getString(2);                
                //create a role object to hold the retrieved information
                Role role = new Role(roleID, roleName);
                //add role object to ArrayList roles
                roles.add(role);
            }
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);   
        }
        return roles;        
    
    }
}
