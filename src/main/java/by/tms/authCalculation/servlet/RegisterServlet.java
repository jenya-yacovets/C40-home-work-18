package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.LoginIsBusy;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.servise.UserService;
import by.tms.authCalculation.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = Validation.register(name, login, password);
            userService.addUser(user);
            resp.getWriter().println("Аккаунт успешно зарегестрирован");
        } catch (ParametersNotPassedException e) {
            resp.getWriter().println("Не переданы обязательные параметры");
        } catch (LoginIsBusy loginIsBusy) {
            resp.getWriter().println("Логин уже занят, попробуй другой");
        }
    }
}
