public class BoundedTypeParameterExample {
    public static void main(String[] args) {
        String str = BoundedUtil.compare("a", "b");

        int result1 = BoundedUtil.compare(10, 20); // int -> Integer
        System.out.println(result1);

        int result2 = BoundedUtil.compare(4.5, 3); // double -> Double
        System.out.println(result2);

    }
}
