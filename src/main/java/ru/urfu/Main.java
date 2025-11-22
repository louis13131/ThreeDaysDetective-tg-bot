package ru.urfu;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        ProcessMain processMain = new ProcessMain();
        if  (args.length != 2) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        String token = args[0];
        String running = args[1];
        processMain.run(token, running);
    }
}
