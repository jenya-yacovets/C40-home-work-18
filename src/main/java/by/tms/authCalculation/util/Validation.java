package by.tms.authCalculation.util;

import by.tms.authCalculation.config.OperationEnum;
import by.tms.authCalculation.entity.Operation;
import by.tms.authCalculation.exception.ParametersNotPassedException;

public class Validation {
    public static Operation operationValidation(String num1, String num2, String method) throws ParametersNotPassedException {
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
