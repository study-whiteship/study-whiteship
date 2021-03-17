class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) { // different number of args
        return a + b + c;
    }

    int add(String a, String b) { // different type of args
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    // Duplicate method add(int, int) in type Calculator
    // String add(int a, int b) { 
    //     return a + b;
    // }
}

public class Overloading {
    public static void main(String[] args) {
        
    }
}
