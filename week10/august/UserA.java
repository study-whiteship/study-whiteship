public class UserA extends Thread {
    private MyCalculator calculator;

    public void setCalculator(MyCalculator calculator) {
        this.setName("UserA");
        this.calculator = calculator;
    }

    public void run() {
        calculator.setMemory(100);
    }
}
