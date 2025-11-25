package ru.urfu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TelegramInteraction extends TelegramLongPollingBot {

    HashMap<String, Game> games;
    private String token;

    TelegramInteraction(String token) {
        this.token = token;
        this.games = new HashMap<>();
    }

    @Override
    public String getBotUsername() {
        return "ThreeDaysDetective_bot";
    }

    @Override
    public String getBotToken() {
        return token;
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

                if (text.equals("Введите имя персонажа:")){
                    sendMessageWithCharacterButtons(text, chatId);
                }
                else if (!text.equals("Игра завершена")){
                    sendMessageWithCommandButtons(text, chatId);
                }


                if (text.equals("Игра завершена") || text.equals(Strings.defeatMessage) ||  text.equals(Strings.victoryMessage)) {
                    sendMessageWithStartButtons("Игра завершена", chatId);
                    games.remove(chatId);
                }
            }
            else {
                text = processCommand.getAnswerBeforeGame(messageText);
                sendMessageWithStartButtons(text, chatId);
            }

            if (text.equals("Игра началась")){
                currentGame.setGameStatus(true);
                sendMessageWithCommandButtons(Strings.welcomeMessage, chatId);
            }
        }
    }

    private void sendMessageWithButtons(KeyboardRow[] rows, String text, String chatId){

        List<KeyboardRow> keyboard = new ArrayList<>(Arrays.asList(rows));

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setKeyboard(keyboard);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(false);

        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        message.setReplyMarkup(replyMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageWithStartButtons(String text, String chatId){
        KeyboardButton button1 = new KeyboardButton("/help");
        KeyboardButton button2 = new KeyboardButton("/start_game");

        KeyboardRow[] rows = new KeyboardRow[] {new KeyboardRow()};
        rows[0] = new KeyboardRow();
        rows[0].add(button1);
        rows[0].add(button2);
        sendMessageWithButtons(rows, text, chatId);
    }

    private void sendMessageWithCharacterButtons(String text, String chatId){
        KeyboardButton button1 = new KeyboardButton("Lidia");
        KeyboardButton button2 = new KeyboardButton("Anna");
        KeyboardButton button3 = new KeyboardButton("Dmitriy");

        KeyboardRow[] rows = new KeyboardRow[2];
        rows[0] = new KeyboardRow();
        rows[1] = new KeyboardRow();
        rows[0].add(button1);
        rows[0].add(button2);
        rows[0].add(button3);

        Game currentGame = games.get(chatId);
        if (currentGame.getPetrStatus() == Victim.Status.ALIVE) {
            KeyboardButton button4 = new KeyboardButton("Petr");
            rows[1].add(button4);
        }
        if  (currentGame.getGrigoriyStatus() == Victim.Status.ALIVE) {
            KeyboardButton button5 = new KeyboardButton("Grigoriy");
            rows[1].add(button5);
        }
        sendMessageWithButtons(rows, text, chatId);
    }

    private void sendMessageWithCommandButtons(String text, String chatId){
        KeyboardButton button1 = new KeyboardButton("/help");
        KeyboardButton button2 = new KeyboardButton("/info");
        KeyboardButton button3 = new KeyboardButton("/talk");
        KeyboardButton button4 = new KeyboardButton("/inspect");
        KeyboardButton button5 = new KeyboardButton("/clue");

        KeyboardRow[] rows = new KeyboardRow[3];
        rows[0] = new KeyboardRow();
        rows[1] = new KeyboardRow();
        rows[2] = new  KeyboardRow();

        rows[0].add(button1);
        rows[0].add(button2);
        rows[0].add(button3);
        rows[1].add(button4);
        rows[1].add(button5);


        Game currentGame = games.get(chatId);
        if (currentGame.getCurrentDay() == Day.DAY3) {
            KeyboardButton button6 = new KeyboardButton("/blame");
            rows[1].add(button6);
        }
        if (currentGame.getCurrentDay() != Day.DAY3) {
            KeyboardButton button7 = new KeyboardButton("/end_the_day");
            rows[1].add(button7);
        }


        KeyboardButton exitButton = new KeyboardButton(("/exit"));
        rows[2].add(exitButton);

        sendMessageWithButtons(rows, text, chatId);
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