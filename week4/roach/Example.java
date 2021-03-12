public class Example {

    public static void main() {
        what(3);
    }

    public static void what(int num) {
        switch (num) {
        case 1:
            System.out.println("1");
        case 2:
            System.out.println("2");
        case 3:
            System.out.println("3");
        default:
            System.out.println("hello");
        }
    }

}
