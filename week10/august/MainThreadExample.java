public class MainThreadExample {
    public static void main(String[] args) {
        MyCalculator calculator = new MyCalculator();
        
        UserA userA = new UserA();
        userA.setCalculator(calculator);
        userA.start();

        UserB userB = new UserB();
        userB.setCalculator(calculator);
        userB.start();
    }
}
