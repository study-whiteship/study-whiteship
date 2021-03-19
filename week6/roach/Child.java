public class Child extends Parent {

    int a = 15;

    void print() {
        System.out.println(a);
        System.out.println(this.a);
        System.out.println(super.a);
    }
}
