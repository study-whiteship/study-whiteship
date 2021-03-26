interface MyInterface {
    void method();

    static void newMethod() {
        System.out.println("This is static method.");
    }
}

public class InterfaceTest {
    public static void main(String[] args) {
        MyInterface.newMethod();
    }
}