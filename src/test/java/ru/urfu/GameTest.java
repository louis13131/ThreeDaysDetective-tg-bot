package ru.urfu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void getAnswerInGameInfoLidiaTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info_lidia");
        String expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.lidia);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoAnnaTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info_anna");
        String expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.anna);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoDmitriyTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info_dmitriy");
        String expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.dmitriy);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoPetrTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info_petr");
        String expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.petr);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoGrigoriyTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info_grigoriy");
        String expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.grigoriy);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameExitTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/exit");
        Assertions.assertEquals("Игра завершена", result);
    }

    @Test
    public void getAnswerInGameIncorrectCommandTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/incorrectCommand");
        Assertions.assertEquals("Такой команды не существует", result);
    }

    @Test
    public void getAnswerInGameStart_gameTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/start_game");
        Assertions.assertEquals("Игра уже началась", result);
    }

    @Test
    public void genAnswerInGameEnd_the_dayOneTimeTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/end_the_day");
        Assertions.assertEquals(Strings.dailyMessage[0], result);
    }

    @Test
    public void genAnswerInGameEnd_the_dayTwoTimesTest() {
        Game game = new Game();
        game.endTheDay();
        String result = game.processCommandInGame("/end_the_day");
        Assertions.assertEquals(Strings.dailyMessage[1], result);
    }

    @Test
    public void genAnswerInGameEnd_the_dayThreeTimesTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/end_the_day");
        Assertions.assertEquals("Это последний день", result);
    }

    @Test
    public void getAnswerInGameBlameLidiaTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame_lidia");
        Assertions.assertEquals(Strings.victoryMessage, result);
    }

    @Test
    public void getAnswerInGameBlameAnnaTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame_anna");
        Assertions.assertEquals(Strings.defeatMessage, result);
    }

    @Test
    public void getAnswerInGameBlameDmitriyTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame_dmitriy");
        Assertions.assertEquals(Strings.defeatMessage, result);
    }

    @Test
    public void getAnswerInGameBlameBeforeDAY3Test() {
        Game game = new Game();
        game.endTheDay();
        String result = game.processCommandInGame("/blame_lidia");
        Assertions.assertEquals("Такой команды не существует", result);
    }
}
