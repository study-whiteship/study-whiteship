public class UserB extends Thread {
    private MyCalculator calculator;

    public void setCalculator(MyCalculator calculator) {
        this.setName("UserB");
        this.calculator = calculator;
    }

    public void run() {
        calculator.setMemory(50);
    }
}
