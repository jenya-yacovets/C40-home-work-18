package by.tms.authCalculation.config;

public enum OperationEnum {
    PLUS {
        public double action(double num1, double num2) {
            return num1 + num2;
        }
        public String getNameParam() {
            return "plus";
        }
        public String getNameUser() {
            return "сумма";
        }
    },
    MINUS {
        public double action(double num1, double num2) {
            return num1 - num2;
        }
        public String getNameParam() {
            return "minus";
        }
        public String getNameUser() {
            return "разность";
        }
    },
    MULTIPLY {
        public double action(double num1, double num2) {
            return num1 * num2;
        }
        public String getNameParam() {
            return "multiply";
        }
        public String getNameUser() {
            return "умножение";
        }
    },
    DIVIDE {
        public double action(double num1, double num2) {
            return num1 / num2;
        }
        public String getNameParam() {
            return "divide";
        }
        public String getNameUser() {
            return "деление";
        }
    };

    public abstract double action(double num1, double num2);
    public abstract String getNameParam();
    public abstract String getNameUser();
}
