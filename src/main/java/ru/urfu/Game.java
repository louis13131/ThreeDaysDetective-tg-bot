package ru.urfu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Killer killer;
    private String[] firstVictimFullName;
    private Victim dmitriyOrlov;
    private Victim annaVoronova;
    private Victim petrVoronov;
    private Victim grigoriyZharov;
    List<Human> characters;
    private NameGenerator nameGenerator;

    private Day currentDay;

    String prevCommand;
    String evidence = "";
    private boolean isRunning = false;
    private boolean waitingForName = false;
    private boolean flagDayShift = false;

    public Game() {
        nameGenerator = new NameGenerator();

        String[][] femaleNames = nameGenerator.generateFemaleNames();
        String[][] maleNames = nameGenerator.generateMaleNames();

        String familySurname = maleNames[0][1];

        String[] familyFemaleName = femaleNames[1];
        String[] familyMaleName = maleNames[2];
        familyFemaleName[1] = familySurname;
        familyMaleName[1] = familySurname;

        firstVictimFullName = maleNames[0];
        killer = new Killer(femaleNames[0], Strings.killer, Strings.killerDialoguesByDay);
        dmitriyOrlov = new Victim(maleNames[1], Strings.dmitriy, Strings.redHerring1DialoguesByDay);
        annaVoronova = new Victim(familyFemaleName, Strings.anna, Strings.redHerring2DialoguesByDay);
        petrVoronov = new Victim(familyMaleName, Strings.petr, Strings.victim2DialoguesByDay);
        grigoriyZharov = new Victim(maleNames[3], Strings.grigoriy, Strings.victim1DialoguesByDay);

        characters = List.of(killer, dmitriyOrlov, annaVoronova, petrVoronov, grigoriyZharov);

        currentDay = Day.DAY1;
    }

    public String processCommandInGame(String instruction){
        String answer = "";

        if (instruction.equals("/info") || instruction.equals("/talk") || instruction.equals("/blame")) {
            waitingForName = true;
            prevCommand = instruction;
            return "Введите имя персонажа:";
        }

        if (!waitingForName){
            answer = processSingleCommand(instruction);
        }
        else {
            Human character = findCharacterByName(instruction);

            if  (character != null) {
                answer = processCharacterCommand(prevCommand, character);
            }else{
                answer = "Такого персонажа не существует";
            }
            waitingForName = false;
        }

        return answer;
    }

    String processSingleCommand(String instruction){
        String answer = switch (instruction){
            case "/help" -> Strings.helpMessage;
            case "/start_game" -> "Игра уже началась";
            case "/inspect" -> {
                if (!flagDayShift){
                    flagDayShift = true;
                    evidence += Strings.evidence[currentDay.ordinal()];
                    yield Strings.inspection[currentDay.ordinal()];
                }
                else{
                    yield "Вы уже нашли все улики сегодня";
                }
            }
            case "/clue" -> {
                if ("".equals(evidence)) {
                    yield "Вы ещё не нашли улик";
                } else {;
                    yield evidence;
                }
            }
            case "/end_the_day" -> {
                flagDayShift = false;
                yield endTheDay();
            }
            case "/exit" -> "Игра завершена";
            default ->  "Такой команды не существует";
        };

        return answer;
    }

    String processCharacterCommand(String instruction, Human character){
        String answer = switch(instruction){
            case "/info" -> character.getInfo();
            case "/talk" -> talkToCharacter(character);
            case "/blame" -> blameCharacter(character);
            default -> "Такого персонажа не существует";
        };

        return answer;
    }

    public String talkToCharacter(Human character){
        String answer = character.getDialogueByDay(currentDay);
        return answer;
    }

    public String blameCharacter(Human character){
        if (currentDay == Day.DAY3){
            String answer = "";
            if (character instanceof Killer){
                answer = Strings.victoryMessage;
            }else if (character instanceof Victim){
                answer = Strings.defeatMessage;
            }
            return answer;
        }

        return "Вынести обвинение можно только на 3 день";
    }

    public String endTheDay(){
        String answer;

        try {
            currentDay = currentDay.next();
        } catch (Exception e) {
            return "Это последний день";
        }

        answer = generateDailyMessage();

        if  (currentDay == Day.DAY2){
            grigoriyZharov.setStatusToDead();
        }
        else {
            petrVoronov.setStatusToDead();
        }

        return answer;
    }

    public String generateWelcomeMessage(){
        String welcomeMessage = String.format(Strings.welcomeMessageTemplate, firstVictimFullName[1],
                firstVictimFullName[0] + " " + firstVictimFullName[1], getCharacterFullNames());
        return welcomeMessage;
    }

    public String generateDailyMessage(){
        String currentDayMessageTemplate = Strings.dailyMessagesTemplate[currentDay.ordinal() - 1];
        String dailyMessage = "";

        if (currentDay == Day.DAY2){
            dailyMessage = String.format(currentDayMessageTemplate, grigoriyZharov.getFullName());
        }
        if (currentDay == Day.DAY3){
            dailyMessage = String.format(currentDayMessageTemplate, petrVoronov.getFullName());
        }
        return dailyMessage;
    }

    public Human findCharacterByName(String name){
        for (Human character : characters){
            if (character.getName().equals(name)){
                return character;
            }
        }
        return null;
    }


    public String[] getCharacterNames(){
        String[] characterNames = new String[characters.size()];
        for (int i = 0; i < characters.size(); i++){
            characterNames[i] = characters.get(i).getName();
        }
        return characterNames;
    }

    public String getCharacterFullNames(){
        return characters.stream()
                .map(Human::getFullName)
                .collect(Collectors.joining(", "));
    }

    public void setGameStatus(boolean isRunning){
        this.isRunning = isRunning;
    }

    public boolean getGameStatus(){
        return isRunning;
    }

    public Victim.Status getPetrStatus(){
        return petrVoronov.getStatus();
    }

    public Victim.Status getGrigoriyStatus(){
        return grigoriyZharov.getStatus();
    }

    public Day getCurrentDay(){
        return currentDay;
    }
}
