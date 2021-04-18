package by.tms.authCalculation.dao;

import by.tms.authCalculation.entity.Operation;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDAO implements DAO{
    private static InMemoryDAO instance;
    private List<User> userList;
    private List<Operation> operationList;

    private int nextUserId = 1;
    private int nextOperationId = 1;

    private InMemoryDAO() {
        userList = new ArrayList<>();
        operationList = new ArrayList<>();
    }

    public static InMemoryDAO getInstance() {
        if(instance == null) instance = new InMemoryDAO();
        return instance;
    }

    @Override
    public int createUser(User user) {
        user.setId(nextUserId++);
        userList.add(user);
        return user.getId();
    }

    @Override
    public User getUserByLogin(String login) throws UserNotFoundException {
        for(User user : userList) {
            if (user.getLogin().equals(login)) return user;
        }
        throw new UserNotFoundException();
    }

    @Override
    public int createOperation(Operation operation) {
        operation.setId(nextOperationId++);
        operationList.add(operation);
        return operation.getId();
    }

    @Override
    public List<Operation> getAllOperationUser(User user) {
        List<Operation> list = new ArrayList<>();

        for (Operation operation : operationList) {
            if(operation.getUser().equals(user)) list.add(operation);
        }

        return list;
    }
}
