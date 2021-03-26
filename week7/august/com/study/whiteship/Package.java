package com.study.whiteship;

public class Package {
    public static void printPackage() {
        System.out.println("This is whiteship package");
    }
    public static void main(String[] args) {
        com.study.blackship.Package.printPackage();
        printPackage();
    }
}
