package by.tms.authCalculation.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if(user != null) {
            session.invalidate();
            resp.getWriter().println("Вы успешно вышли из аккаунта");
        } else {
            resp.getWriter().println("Вы не авторизованы, чтобы выйти из аккаунта");
        }
    }
}
