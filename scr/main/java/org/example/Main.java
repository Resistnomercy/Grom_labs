package org.example;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startComputer();
        computer.runProgram("Text Editor");
        computer.shutdownComputer();

    }
}