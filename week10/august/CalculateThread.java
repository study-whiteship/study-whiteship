public class CalculateThread extends Thread {
    public CalculateThread(String name) {
        setName(name);
    }

    public void run() {
        for (int i = 0; i < 200000000; i++) {
        }
        System.out.println(getName());
    }
}
