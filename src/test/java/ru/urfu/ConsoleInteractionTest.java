package ru.urfu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsoleInteractionTest {

    @Test
    public void processCommandInGameHelpTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/help");
        Assertions.assertEquals(Strings.helpMessage, result);
    }

    @Test
    public void getAnswerBeforeHelpTest() {
        ProcessCommand processCommand = new ProcessCommand();
        String result = processCommand.getAnswerBeforeGame("/help");
        Assertions.assertEquals(Strings.helpMessage, result);
    }

    @Test
    public void getAnswerBeforeStartTest() {
        ProcessCommand processCommand = new ProcessCommand();
        String result = processCommand.getAnswerBeforeGame("/start");
        Assertions.assertEquals(Strings.helloMessage, result);
    }

    @Test
    public void getAnswerBeforeStartGameTest() {
        ProcessCommand processCommand = new ProcessCommand();
        String result = processCommand.getAnswerBeforeGame("/start_game");
        Assertions.assertEquals("Игра началась", result);
    }

    @Test
    public void getAnswerBeforeIncorrectTest() {
        ProcessCommand processCommand = new ProcessCommand();
        String result = processCommand.getAnswerBeforeGame("/not_comand");
        Assertions.assertEquals("Такой команды не существует", result);
    }
}
