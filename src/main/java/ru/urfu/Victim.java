package ru.urfu;

public class Victim extends Human{
    private enum Status{
        ALIVE,
        DEAD
    }

    private Status currentStatus;

    protected Victim(String[] character) {
        super(character);
        currentStatus = Status.ALIVE;
    }

    public Status getStatus() {
        return currentStatus;
    }
}
