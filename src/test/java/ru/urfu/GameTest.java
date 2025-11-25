package ru.urfu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void getAnswerInGameInfoLidiaTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Lidia");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.lidia);
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/info");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("lidia");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.lidia);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoAnnaTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Anna");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.anna);
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/info");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("anna");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.anna);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoDmitriyTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Dmitriy");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.dmitriy);
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/info");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("dmitriy");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.dmitriy);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoPetrTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Petr");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.petr);
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/info");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("petr");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.petr);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoGrigoriyTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Grigoriy");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.grigoriy);
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/info");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Grigoriy");
        expected = String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", Strings.grigoriy);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameInfoIvanTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/info");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Ivan");
        expected = "Такого персонажа не существует";
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
        String result = game.processCommandInGame("/blame");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Lidia");
        Assertions.assertEquals(Strings.victoryMessage, result);
        result = game.processCommandInGame("/blame");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("lidia");
        Assertions.assertEquals(Strings.victoryMessage, result);
    }

    @Test
    public void getAnswerInGameBlameAnnaTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Anna");
        Assertions.assertEquals(Strings.defeatMessage, result);
        result = game.processCommandInGame("/blame");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("anna");
        Assertions.assertEquals(Strings.defeatMessage, result);
    }

    @Test
    public void getAnswerInGameBlameDmitriyTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Dmitriy");
        Assertions.assertEquals(Strings.defeatMessage, result);
        result = game.processCommandInGame("/blame");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("dmitriy");
        Assertions.assertEquals(Strings.defeatMessage, result);
    }

    @Test
    public void getAnswerInGameBlamePetrTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Petr");
        Assertions.assertEquals("Этот персонаж мёртв, его нельзя обвинить", result);
        result = game.processCommandInGame("/blame");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("petr");
        Assertions.assertEquals("Этот персонаж мёртв, его нельзя обвинить", result);
    }

    @Test
    public void getAnswerInGameBlameGrigoriyTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Grigoriy");
        Assertions.assertEquals("Этот персонаж мёртв, его нельзя обвинить", result);
        result = game.processCommandInGame("/blame");
        expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("grigoriy");
        Assertions.assertEquals("Этот персонаж мёртв, его нельзя обвинить", result);
    }

    @Test
    public void getAnswerInGameBlameIvanTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Ivan");
        Assertions.assertEquals("Такого персонажа не существует", result);
    }

    @Test
    public void getAnswerInGameBlameBeforeDAY3Test() {
        Game game = new Game();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Lidia");
        Assertions.assertEquals("Вынести обвинение можно только на 3 день", result);
    }

    @Test
    public void getAnswerInGameTalkLidiaTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Lidia");
        String expected = Strings.lidiaDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("lidia");
        expected = Strings.lidiaDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Lidia");
        expected = Strings.lidiaDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("lidia");
        expected = Strings.lidiaDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Lidia");
        expected = Strings.lidiaDialoguesByDay[2];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("lidia");
        expected = Strings.lidiaDialoguesByDay[2];
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameTalkAnnaTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Anna");
        String expected = Strings.annaDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("anna");
        expected = Strings.annaDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Anna");
        expected = Strings.annaDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("anna");
        expected = Strings.annaDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Anna");
        expected = Strings.annaDialoguesByDay[2];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("anna");
        expected = Strings.annaDialoguesByDay[2];
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameTalkDmitriyTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Dmitriy");
        String expected = Strings.dmitriyDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("dmitriy");
        expected = Strings.dmitriyDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Dmitriy");
        expected = Strings.dmitriyDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("dmitriy");
        expected = Strings.dmitriyDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Dmitriy");
        expected = Strings.dmitriyDialoguesByDay[2];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("dmitriy");
        expected = Strings.dmitriyDialoguesByDay[2];
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameTalkPetrTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Petr");
        String expected = Strings.petrDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("petr");
        expected = Strings.petrDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Petr");
        expected = Strings.petrDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("petr");
        expected = Strings.petrDialoguesByDay[1];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Petr");
        expected = Strings.deathMessage;
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("petr");
        expected = Strings.deathMessage;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameTalkGrigoriyTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Grigoriy");
        String expected = Strings.grigoriyDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("grigoriy");
        expected = Strings.grigoriyDialoguesByDay[0];
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Grigoriy");
        expected = Strings.deathMessage;
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("grigoriy");
        expected = Strings.deathMessage;
        Assertions.assertEquals(expected, result);
        game.endTheDay();
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("Grigoriy");
        expected = Strings.deathMessage;
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/talk");
        Assertions.assertEquals("Введите имя персонажа:", result);
        result = game.processCommandInGame("grigoriy");
        expected = Strings.deathMessage;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameTalkIvanTest() {
        Game game = new Game();
        game.endTheDay();
        game.endTheDay();
        String result = game.processCommandInGame("/talk");
        String expected = "Введите имя персонажа:";
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("Ivan");
        Assertions.assertEquals("Такого персонажа не существует", result);
    }

    @Test
    public void getAnswerInGameInspectTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/inspect");
        String expected = Strings.inspection[0];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/inspect");
        expected = "Вы уже нашли все улики сегодня";
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/end_the_day");
        result = game.processCommandInGame("/inspect");
        expected = Strings.inspection[1];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/inspect");
        expected = "Вы уже нашли все улики сегодня";
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/end_the_day");
        result = game.processCommandInGame("/inspect");
        expected = Strings.inspection[2];
        Assertions.assertEquals(expected, result);
        result = game.processCommandInGame("/inspect");
        expected = "Вы уже нашли все улики сегодня";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAnswerInGameClueTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/clue");
        String expected = "Вы ещё не нашли улик";
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/inspect");
        result = game.processCommandInGame("/clue");
        expected = Strings.evidence[0];
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/end_the_day");
        result = game.processCommandInGame("/clue");
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/inspect");
        result = game.processCommandInGame("/clue");
        expected += Strings.evidence[1];
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/end_the_day");
        result = game.processCommandInGame("/clue");
        Assertions.assertEquals(expected, result);
        game.processCommandInGame("/inspect");
        result = game.processCommandInGame("/clue");
        expected += Strings.evidence[2];
        Assertions.assertEquals(expected, result);
    }
}
