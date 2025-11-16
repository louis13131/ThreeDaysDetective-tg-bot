package ru.urfu;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        ProcessMain processMain = new ProcessMain();
        processMain.run();
    }
}
