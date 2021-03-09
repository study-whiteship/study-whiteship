public class Example {

    public static void main(String[] args) {
        System.out.println(11 & 11); // 1011 & 1101 =>
        System.out.println(10 & 20); // 1010 & 10100 => 0000
        System.out.println(11 & 15); // 1011 & 1111 => 1011

        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(4));
        bitSum(3, 4);

        exclusive(11, 11);

        System.out.println(2 << 3);

        System.out.println(4 >> 1);

    }

    public static void bitSum(int a, int b) {
        System.out.println(a | b);
    }

    public static void exclusive(int a, int b) {
        System.out.println(a ^ b);
    }

}