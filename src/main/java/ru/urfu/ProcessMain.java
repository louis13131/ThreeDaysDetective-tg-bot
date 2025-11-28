package ru.urfu;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ProcessMain
{
    public void run(String token, String running) throws TelegramApiException {

        if (running.equals("telegram") || running.equals("both")) {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramInteraction(token));
        }

        if (running.equals("console") || running.equals("both")) {
            ConsoleInteraction helloMessage = new ConsoleInteraction();
            helloMessage.print(Strings.helloMessage);
            ConsoleInteraction consoleInteraction = new ConsoleInteraction();
            ProcessCommand processCommand = new ProcessCommand();

            while (true) {
                consoleInteraction.print("Введите команду: ");
                String instruction = consoleInteraction.getCommand();
                String answer = processCommand.getAnswerBeforeGame(instruction);
                consoleInteraction.print(answer);
                if (answer.equals("Игра началась")) {
                    Game game = new Game();
                    consoleInteraction.print(Strings.welcomeMessage);
                    while (true) {
                        consoleInteraction.print("Введите команду: ");
                        instruction = consoleInteraction.getCommand();
                        answer = game.processCommandInGame(instruction);

                        if (answer.equals("Введите имя персонажа:")){
                            consoleInteraction.print(answer);
                            instruction = consoleInteraction.getCommand();
                            answer = game.processCommandInGame(instruction);
                        }

                        consoleInteraction.print(answer);

                        if (answer.equals("Игра завершена") || answer.equals(Strings.defeatMessage) || answer.equals(Strings.victoryMessage)) {
                            break;
                        }
                    }
                }
            }
        }
    }

}
