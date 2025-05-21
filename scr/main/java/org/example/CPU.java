package org.example;

public class CPU {
    public void freeze() {
        System.out.println("CPU: freeze");
    }

    public void execute(String command) {
        System.out.println("CPU: execute '" + command + "'");
    }
}

