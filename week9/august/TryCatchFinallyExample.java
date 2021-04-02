public class TryCatchFinallyExample {
    // public static void main(String[] args) {
    //     String data1 = null;
    //     String data2 = null;

    //     try {
    //         data1 = args[0];
    //         data2 = args[1];
    //         System.out.println("args[0]" + data1);
    //         System.out.println("args[1]" + data2);
    //     } catch(ArrayIndexOutOfBoundsException e) {
    //         System.out.println("usage:");
    //         System.out.print("java TryCatchFinallyExample ");
    //         System.out.println("argument1 argument2");
    //         return;
    //     }
        
    //     try {
    //         int value1 = Integer.parseInt(data1);
    //         int value2 = Integer.parseInt(data2);
    //         int result = value1 + value2;
    //         System.out.println(data1 + "+" + data2 + "=" + result);
    //     } catch (NumberFormatException e) {
    //         System.out.println("Cannot format to number.");
    //     } finally {
    //         System.out.println("Enter the proper arguments. (number format)");    
    //     }

    // }

    public static void main(String[] args) {
        String data1 = null;
        String data2 = null;

        try {
            data1 = args[0];
            data2 = args[1];
            System.out.println("args[0]" + data1);
            System.out.println("args[1]" + data2);

            int value1 = Integer.parseInt(data1);
            int value2 = Integer.parseInt(data2);
            int result = value1 + value2;
            System.out.println(data1 + "+" + data2 + "=" + result);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("usage:");
            System.out.print("java TryCatchFinallyExample ");
            System.out.println("argument1 argument2");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Cannot format to number.");
        } finally {
            System.out.println("Enter the proper arguments. (number format)");    
        }
    }
}
