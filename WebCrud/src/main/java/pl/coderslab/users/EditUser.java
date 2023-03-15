package pl.coderslab.users;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class EditUser extends HttpServlet {
    User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDao();
        user = userDao.read(id);
        request.setAttribute("name", user.getName());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("password", user.getPassword());
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        System.out.println(name + "" + email + "" + password);
        UserDao userDao = new UserDao();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashPassword);

        System.out.println(user);
        userDao.update(user);
        response.sendRedirect("/user/list");

    }
}
