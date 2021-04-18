package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;
import by.tms.authCalculation.servise.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if(user == null) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            try {
                User userAuth = userService.getUser(login, password);
                resp.getWriter().println("Вы успешно аторизовались");
                req.getSession().setAttribute("user", userAuth);
            } catch (UserNotFoundException e) {
                resp.getWriter().println("Логин или пароль введен не верно");
            } catch (ParametersNotPassedException e) {
                resp.getWriter().println("Не переданы обязательные параметры");
            }
        } else {
            resp.getWriter().println("Вы уже авторизованы. Выйдите из текуще аккаунта и авторизуйтесь в другой");
        }
    }
}
