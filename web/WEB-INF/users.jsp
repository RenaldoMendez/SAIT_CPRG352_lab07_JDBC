<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account</title>
    </head>

    <body>

        <h2>Add User</h2>

        <form method="post" action="users">
            <input type="text" placeholder="Email" name="email" value=""><br>
            <input type="text" placeholder="First Name" name="first_name" value=""><br>
            <input type="text" placeholder="Last Name" name="last_name" value=""><br>
            <input type="password" placeholder="Password" name="password" value=""><br>
            <select id="select_role" name="user_role">
                <option value="system_admin">System Administrator</option>
                <option value="regular_user">Regular User</option>
                <option value="company_admin">Company Administrator</option>
            </select>
            <br>
            <input type="submit" value="Save">
            <input type="hidden" name="action" value="new user">
        </form>


        <form method="post" action="users">
            <h2>Manage Users</h2>
            <table>
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="userObject" items="${users}">
                    <tr>
                        <td>${userObject.email}</td>
                        <td>${userObject.firstName}</td>
                        <td>${userObject.lastName}</td>
                        <td>${userObject.role}</td>
                        <td>${userObject.isActive}</td>
                        <td><input type="submit" value="Edit"><input type="hidden" name="action" value="edit user"></td>
                        <td><input type="submit" value="Delete"><input type="hidden" name="action" value="delete user"></td>
                    </tr>
                </c:forEach> 
            </table>
        </form>
        
        <!--displays if the user clicks edit-->
        <c:if test="action == edit user">
            <h2>Edit User</h2>
            <form method="post" action="users">
                <input type="text" placeholder="Email" name="email" value="${useremail}">
                <input type="text" placeholder="First Name" name="first_name" value="${firstname}">
                <input type="text" placeholder="Last Name" name="last_name" value="${lastname}"> 
                <input type="text" placeholder="Email" name="role" value="${userrole}">

                <input type="submit" value="Save">
                <input type="hidden" name="action" value="save">

                <input type="submit" value="Cancel">
                <input type="hidden" name="action" value="cancel">
            </form>
        </c:if>
    </body>
</html>
