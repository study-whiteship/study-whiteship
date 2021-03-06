class Variables {
    static int classVariable = 1;
    int instanceVariable = 1;

    void method() {
        int localVariable = 1;
        System.out.println(localVariable);
        localVariable++;
        System.out.println(localVariable);
    }
}

class VariableLifetime {
    public static void main(String[] args) {
        System.out.println("Class Variable Lifetime Test");
        for (int i = 0; i < 3; i++) {
            System.out.println(Variables.classVariable);
            Variables.classVariable++;
            System.out.println(Variables.classVariable);    
        }
       
        System.out.println("Instance Variable Lifetime Test");
        Variables variables = new Variables();
        for (int i = 0; i < 3; i++) {
            variables = new Variables();
            System.out.println(variables.instanceVariable);
            variables.instanceVariable++;
            System.out.println(variables.instanceVariable);
        }
        
        System.out.println("Local Variable Lifetime Test");
        variables = new Variables();
        for (int i = 0; i < 3; i++) {
            variables.method();
        }
    }
}