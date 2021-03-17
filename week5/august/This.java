class Car {
    String color;
    String tire;
    int velocity;

    Car() {
        this("Red", "Kumho", 80);
    }

    Car(String color) {
        this(color, "Kumho", 80);
    }

    Car(String color, String tire, int velocity) {
        this.color = color;
        this.tire = tire;
        this.velocity = velocity;
    }

}

public class This {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.color + " " + car.tire + " " + car.velocity);
        car = new Car("Black");
        System.out.println(car.color + " " + car.tire + " " + car.velocity);
        car = new Car("Pink", "Hankook", 160);
        System.out.println(car.color + " " + car.tire + " " + car.velocity);
    }
}
