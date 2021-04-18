package by.tms.authCalculation.servise;

import by.tms.authCalculation.dao.DAO;
import by.tms.authCalculation.dao.InMemoryDAO;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.LoginIsBusy;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;

public class UserService {
    private DAO dao = InMemoryDAO.getInstance();

    public void addUser(User user) throws ParametersNotPassedException, LoginIsBusy {
        if(user.getName() == null || user.getLogin() == null || user.getPassword() == null) {
            throw new ParametersNotPassedException();
        }

        try {
            dao.getUserByLogin(user.getLogin());
            throw new LoginIsBusy();
        } catch (UserNotFoundException e) {
            dao.createUser(user);
        }
    }

    public User getUser(String login, String password) throws UserNotFoundException, ParametersNotPassedException {
        if(login == null || password == null) {
            throw new ParametersNotPassedException();
        }

        User userByLogin = dao.getUserByLogin(login);

        if(userByLogin.getPassword().equals(password)) {
            return userByLogin;
        }
        throw new UserNotFoundException();
    }

}
