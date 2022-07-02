package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //display users JSP
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //store action that was done by the user
        String action = request.getParameter("action");
        
        //Store variables
        String userEmail = request.getParameter("email");
        String userFirstName = request.getParameter("first_name");
        String userLastName = request.getParameter("last_name");
        String userPassword = request.getParameter("password");
        String userRole = request.getParameter("role");
        
        boolean userStatus = false;
        UserService userService = new UserService();
        
        try{
        // decide the flow of control based on the user's actions
        switch (action) {
            case "new user":
                userService = new UserService();
                userStatus = true;
                userService.insert(userEmail, userStatus, userFirstName, userLastName, userPassword, userRole);

                break;
            case "edit user":
                userStatus = true;
                userService.update(userStatus, userFirstName, userLastName, userPassword, userRole);
                break;
            case "delete user":
                userService.delete(userEmail);
                break;
        }
        }catch(SQLException e){
            e.printStackTrace();
        }

    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;
    }
    
    
}
