public class Main {

    public static void main(String[] args) {
        StaticOuter staticOuter = new StaticOuter();
        Outer outer = new Outer();

        // Outer.Inner inner = new Outer.Inner(); => 안됨

        StaticOuter.Inner staticOuterInner = new StaticOuter.Inner(); //
        System.out.println(staticOuter.x);
    }
}
