package ru.urfu;

public class Victim extends Human{
    public enum Status{
        ALIVE,
        DEAD
    }

    private Status currentStatus;

    protected Victim(String[] nameParts, String[] character, String[] dialogues) {
        super(nameParts, character,  dialogues);
        currentStatus = Status.ALIVE;
    }

    @Override
    public String getDialogueByDay(Day currentDay){
        if (this.currentStatus == Status.ALIVE) {
            return super.getDialogueByDay(currentDay);
        }
        return Strings.deathMessage;
    }

    public Status getStatus() {
        return currentStatus;
    }
    public void setStatusToDead() {
        currentStatus = Status.DEAD;
    }
}
