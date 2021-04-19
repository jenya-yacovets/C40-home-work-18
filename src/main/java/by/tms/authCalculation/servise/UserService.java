package by.tms.authCalculation.servise;

import by.tms.authCalculation.dao.DAO;
import by.tms.authCalculation.dao.InMemoryDAO;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.LoginIsBusy;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;

public class UserService {
    private DAO dao = InMemoryDAO.getInstance();

    public void addUser(User user) throws LoginIsBusy {

        try {
            dao.getUserByLogin(user.getLogin());
            throw new LoginIsBusy();
        } catch (UserNotFoundException e) {
            dao.createUser(user);
        }
    }

    public User getUser(User user) throws UserNotFoundException, ParametersNotPassedException {

        User userByLogin = dao.getUserByLogin(user.getLogin());

        if(userByLogin.getPassword().equals(user.getPassword())) {
            return userByLogin;
        }
        throw new UserNotFoundException();
    }

}
