public class ThrowsExample {
    public static void main(String[] args) {
        ThrowsExample throwsExample = new ThrowsExample();
        throwsExample.method1();
    }

    public void method1() {
        try {
            method2();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred : " + e.getMessage());
            // 예외 처리 코드
        }
    }

    public void method2() throws ClassNotFoundException {
        Class clazz = Class.forName("java.lang.String2");
    }
}
