package by.tms.authCalculation.util;

import by.tms.authCalculation.config.OperationEnum;
import by.tms.authCalculation.entity.Operation;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.ParametersNotPassedException;

public class Validation {
    public static User register(String name, String login, String password) throws ParametersNotPassedException {
        if(name == null || login == null || password == null) {
            throw new ParametersNotPassedException();
        }
        return new User(name, login, password);
    }

    public static User authorization(String login, String password) throws ParametersNotPassedException {
        if(login == null || password == null) {
            throw new ParametersNotPassedException();
        }
        return new User(login, password);
    }

    public static Operation operation(String num1, String num2, String method) throws ParametersNotPassedException {
        Operation operation = new Operation();

        try {
            operation.setNum1(Double.parseDouble(num1));
            operation.setNum2(Double.parseDouble(num2));
        } catch(NullPointerException e) {
            throw new ParametersNotPassedException();
        }

        for(OperationEnum item : OperationEnum.values()) {
            if(item.getNameParam().equals(method)) {
                operation.setOperation(item);
                break;
            }
        }

        if(operation.getOperation() == null) throw new ParametersNotPassedException();

        return operation;
    }
}
