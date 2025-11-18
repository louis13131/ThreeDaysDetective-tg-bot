package ru.urfu;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        ProcessMain processMain = new ProcessMain();
        if  (args.length != 1) {
            String defaultRunning = "both";
            processMain.run(defaultRunning);
        }
        else {
            String running = args[0];
            processMain.run(running);
        }
    }
}
