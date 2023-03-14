package pl.coderslab.users;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/users/add.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    UserDao userDao = new UserDao();


    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt(10));
    User user = new User();
    user.setEmail(email);
    user.setName(name);
    user.setPassword(hashPassword);
    userDao.create(user);
    response.sendRedirect("/user/list");
    }
}
