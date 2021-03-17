class Computer {
    double cpu = 2.4;
    int core = 2;
    int memory = 256;

    Computer() { 

    }
  
    Computer(double cpu) {
        this.cpu = cpu;
    }

    Computer(double cpu, int core, int memory) {
        this.cpu = cpu;
        this.core = core;
        this.memory = memory;
    }
}

public class Constructor {
    public static void main(String[] args) {
        Computer computer = new Computer();
    }
}