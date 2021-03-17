class Television {
    String color;
    boolean power;
    int channel;

    void power() {
        power = !power;
    }

    void channelUp() {
        ++channel;
    }

    void channelDown() {
        --channel;
    }
}

public class ClassTest {
    public static void main(String[] args) {
        Television television;          // declare reference type variable
        television = new Television();  // instantiate Television class
        new Television();               // instantiated but cannot be used (lost soul)

        // use the class field and method by instance
        television.channel = 7;
        television.channelUp();
        System.out.println("Current channel is : " + television.channel);
        television.channelDown();
        System.out.println("Current channel is : " + television.channel);
    }
}