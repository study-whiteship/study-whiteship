public class Operator {
    public static void main(String[] args) {
        int operand1 = 10;
        int operand2 = 4;

        System.out.printf("%d + %d = %d\n", operand1, operand2, operand1 + operand2);
        System.out.printf("%d - %d = %d\n", operand1, operand2, operand1 - operand2);
        System.out.printf("%d * %d = %d\n", operand1, operand2, operand1 * operand2);
        System.out.printf("%d / %d = %d\n", operand1, operand2, operand1 / operand2);
        System.out.printf("%d / %f = %f\n", operand1, (float) operand2, operand1 / (float) operand2);

        int start = 2_100_000_000;
        int end = 2_100_000_000;
        int mid = (start + end) / 2;        // => overflow can be occurred!!
        System.out.println(mid);
        mid = start + (end - start) / 2;    // => avoid overflow
        System.out.println(mid);
        mid = (start + end) >>> 1;          // => avoid overflow
        System.out.println(mid);
    }
}
