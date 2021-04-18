package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.Operation;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;
import by.tms.authCalculation.servise.OperationService;
import by.tms.authCalculation.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/operation")
public class OperationServlet extends HttpServlet {
    private OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        Object user = req.getSession().getAttribute("user");

        if(user != null) {

            String num1 = req.getParameter("num1");
            String num2 = req.getParameter("num2");
            String method = req.getParameter("method");

            try {
                Operation operation = Validation.operationValidation(num1, num2, method);
                operation.setUser((User) user);
                operationService.addOperation(operation);
                resp.getWriter().format("Результат: %f", operation.result());
            } catch (ParametersNotPassedException e) {
                resp.getWriter().println("Не переданы обязательные параметры или указаны не верно");
            }

        } else {
            resp.getWriter().println("Вы не авторизованы! Для того что бы воспользоваться калькулятором для начала авторизуйтесь. <a href='/authorization'>Авторизоваться</a>");
        }
    }
}
