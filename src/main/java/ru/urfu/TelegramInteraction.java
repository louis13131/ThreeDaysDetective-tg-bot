package ru.urfu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class TelegramInteraction extends TelegramLongPollingBot {

    HashMap<String, Game> games;

    TelegramInteraction() {

        this.games = new HashMap<>();
    }

    @Override
    public String getBotUsername() {
        return "ThreeDaysDetective_bot";
    }

    @Override
    public String getBotToken() {
        return "8358705799:AAGnAFS3FhIZ-I0SLgHDmMhSDRhM8dZUOLg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());
            ProcessCommand processCommand = new ProcessCommand();
            String text;
            Game currentGame;

            if (games.get(chatId) == null){
                Game game = new Game();
                games.put(chatId, game);
            }

            currentGame = games.get(chatId);

            if (games.get(chatId).getGameStatus()) {
                text = currentGame.processCommandInGame(messageText);

                if (text.equals("Игра завершена")){
                    currentGame.setGameStatus(false);
                }
            }
            else
                text = processCommand.getAnswerBeforeGame(messageText);

            if (text.equals("Игра началась")){
                currentGame.setGameStatus(true);
            }

            execute(text, chatId);
        }
    }

    public void execute(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}