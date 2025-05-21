package org.example;

public class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    private OperatingSystem os;
    private Program program;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
        this.os = new OperatingSystem();
        this.program = new Program();
    }

    public void startComputer() {
        System.out.println("Starting computer...");
        cpu.freeze();
        String data = hardDrive.read(0, 1024);
        memory.load(0, data);
        os.boot();
        System.out.println("Computer is ready to use.");
    }

    public void runProgram(String name) {
        program.run(name);
    }

    public void shutdownComputer() {
        System.out.println("Shutting down the computer...");
        System.out.println("Saving work...");
        os.shutdown();
        System.out.println("Computer is off.");
    }
}

