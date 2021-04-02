public class ArrayIndexOutOfBoundsExceptionHandlingExample {
    public static void main(String[] args) {
        if (args.length == 2) {
            String data1 = args[0];
            String data2 = args[1];
            
            System.out.println("args[0]" + data1);
            System.out.println("args[1]" + data2);
        } else {
            System.out.println("usage:");
            System.out.print("java ArrayIndexOutOfBoundsExceptionHandlingExample ");
            System.out.println("argument1 argument2");
        }
        
    }
}