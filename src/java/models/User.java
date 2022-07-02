package models;
/**
 * * informed by the user table in the database
 * @author Renaldo
 */
public class User {
    //should be uneditable
    private String email;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    public User() {
    }

    
    public User(String email, boolean isActive, String first_name, String last_name, String password, String role) {
        this.email = email;
        this.isActive = isActive;
        this.firstName = first_name;
        this.lastName = last_name;
        this.password = password;
        this.role = role;
    }

    public User(String email, boolean isActive, String first_name, String last_name, String role) {
        this.email = email;
        this.isActive = isActive;
        this.firstName = first_name;
        this.lastName = last_name;
        this.role = role;
    }

    public User(boolean isActive, String firstName, String lastName, String password, String role) {
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "User{" + "email=" + email + ", isActive=" + isActive + ", first_name=" + firstName + ", last_name=" + lastName + '}';
    }

    
    
    
    
}
