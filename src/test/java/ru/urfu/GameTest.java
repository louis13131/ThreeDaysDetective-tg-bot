package ru.urfu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GameTest {

    @Test
    public void getAnswerInGameInfoKillerTest() {
        Game game = new Game();
        Human killer = game.characters.get(0);

        String result = game.processCommandInGame("/info");
        Assertions.assertEquals("Введите имя персонажа:", result);

        result = game.processCommandInGame(killer.getName());
        Assertions.assertEquals(killer.getInfo(), result);
    }

    @Test
    public void getAnswerInGameInfoRedHerring2Test() {
        Game game = new Game();
        Human wife = game.characters.get(2);

        String result = game.processCommandInGame("/info");
        Assertions.assertEquals("Введите имя персонажа:", result);

        result = game.processCommandInGame(wife.getName());
        Assertions.assertEquals(wife.getInfo(), result);
    }

    @Test
    public void getAnswerInGameInfoRedHerring1Test() {
        Game game = new Game();
        Human doctor = game.characters.get(1);

        String result = game.processCommandInGame("/info");
        Assertions.assertEquals("Введите имя персонажа:", result);

        result = game.processCommandInGame(doctor.getName());
        Assertions.assertEquals(doctor.getInfo(), result);
    }

    @Test
    public void getAnswerInGameInfoVictim2Test() {
        Game game = new Game();
        Human brother = game.characters.get(4);

        String result = game.processCommandInGame("/info");
        Assertions.assertEquals("Введите имя персонажа:", result);

        result = game.processCommandInGame(brother.getName());
        Assertions.assertEquals(brother.getInfo(), result);
    }

    @Test
    public void getAnswerInGameInfoVictim1Test() {
        Game game = new Game();
        Human partner = game.characters.get(3);

        String result = game.processCommandInGame("/info");
        Assertions.assertEquals("Введите имя персонажа:", result);

        result = game.processCommandInGame(partner.getName());
        Assertions.assertEquals(partner.getInfo(), result);
    }

    @Test
    public void getAnswerInGameInfoNonExistentTest() {
        Game game = new Game();
        game.processCommandInGame("/info");
        String result = game.processCommandInGame("NonExistentName123");
        Assertions.assertEquals("Такого персонажа не существует", result);
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
    public void genAnswerInGameEnd_the_daySequenceTest() {
        Game game = new Game();
        String result = game.processCommandInGame("/end_the_day");
        Human victim1 = game.characters.get(3);
        Assertions.assertTrue(result.contains(victim1.getFullName()));

        result = game.processCommandInGame("/end_the_day");
        Human victim2 = game.characters.get(4);
        Assertions.assertTrue(result.contains(victim2.getFullName()));

        result = game.processCommandInGame("/end_the_day");
        Assertions.assertEquals("Это последний день", result);
    }

    @Test
    public void getAnswerInGameBlameKillerTest() {
        Game game = new Game();
        Human killer = game.characters.get(0);
        game.endTheDay();
        game.endTheDay();

        String result = game.processCommandInGame("/blame");
        Assertions.assertEquals("Введите имя персонажа:", result);

        result = game.processCommandInGame(killer.getName());
        Assertions.assertEquals(Strings.victoryMessage, result);
    }

    @Test
    public void getAnswerInGameBlameInnocentTest() {
        Game game = new Game();
        Human wife = game.characters.get(2);
        game.endTheDay();
        game.endTheDay();

        game.processCommandInGame("/blame");
        String result = game.processCommandInGame(wife.getName());
        Assertions.assertEquals(Strings.defeatMessage, result);
    }

    @Test
    public void getAnswerInGameBlameDeadVictimTest() {
        Game game = new Game();
        Human victim2 = game.characters.get(4); // Петр/Брат
        game.endTheDay();
        game.endTheDay(); // Теперь он мертв

        game.processCommandInGame("/blame");
        String result = game.processCommandInGame(victim2.getName());
    }

    @Test
    public void getAnswerInGameBlameBeforeDay3Test() {
        Game game = new Game();
        game.endTheDay();
        String result = game.processCommandInGame("/blame");
        Assertions.assertEquals("Введите имя персонажа:", result);

        Human killer = game.characters.get(0);
        result = game.processCommandInGame(killer.getName());
        Assertions.assertEquals("Вынести обвинение можно только на 3 день", result);
    }

    @Test
    public void getAnswerInGameTalkKillerTest() {
        Game game = new Game();
        Human killer = game.characters.get(0);

        game.processCommandInGame("/talk");
        String result = game.processCommandInGame(killer.getName());
        Assertions.assertEquals(killer.getDialogueByDay(Day.DAY1), result);

        game.endTheDay();
        game.processCommandInGame("/talk");
        result = game.processCommandInGame(killer.getName());
        Assertions.assertEquals(killer.getDialogueByDay(Day.DAY2), result);

        game.endTheDay();
        game.processCommandInGame("/talk");
        result = game.processCommandInGame(killer.getName());
        Assertions.assertEquals(killer.getDialogueByDay(Day.DAY3), result);
    }

    @Test
    public void getAnswerInGameTalkVictim1Test() {
        Game game = new Game();
        Human victim1 = game.characters.get(3); // Григорий

        game.processCommandInGame("/talk");
        String result = game.processCommandInGame(victim1.getName());
        Assertions.assertEquals(victim1.getDialogueByDay(Day.DAY1), result);

        game.endTheDay(); // Он умирает
        game.processCommandInGame("/talk");
        result = game.processCommandInGame(victim1.getName());
        Assertions.assertEquals(Strings.deathMessage, result);
    }
}