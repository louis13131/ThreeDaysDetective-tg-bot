package ru.urfu;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Killer killer;
    private String[] firstVictimFullName;
    private RedHerring redHerring1;
    private RedHerring redHerring2;
    private Victim victim1;
    private Victim victim2;
    List<Human> characters;
    private NameGenerator nameGenerator;

    private Day currentDay;

    String prevCommand;
    private boolean isRunning = false;
    private boolean waitingForName = false;
    private boolean waitingAnswer = false;
    private boolean flagDayShift = false;
    Evidence evidence = new Evidence();

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
        redHerring1 = new RedHerring(maleNames[1], Strings.dmitriy, Strings.redHerring1DialoguesByDay);
        redHerring2 = new RedHerring(familyFemaleName, Strings.anna, Strings.redHerring2DialoguesByDay);
        victim1 = new Victim(maleNames[3], Strings.grigoriy, Strings.victim1DialoguesByDay);
        victim2 = new Victim(familyMaleName, Strings.petr, Strings.victim2DialoguesByDay);

        characters = List.of(killer, redHerring1, redHerring2, victim1, victim2);

        currentDay = Day.DAY1;
    }

    public String processCommandInGame(String instruction){
        String answer = "";

        if (instruction.equals("/info") || instruction.equals("/talk") || instruction.equals("/blame")) {
            waitingForName = true;
            prevCommand = instruction;
            return "Введите имя персонажа:";
        }

        if (instruction.equals("/inspect")){
            if (!waitingAnswer){
                return Puzzles.riddles[currentDay.ordinal()];
            }
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
                    yield evidence.inspection(currentDay.ordinal());
                }
                else{
                    yield "Вы уже нашли все улики сегодня";
                }
            }
            case "/clue" -> {
                if ("".equals(evidence.evidenceFound)) {
                    yield "Вы ещё не нашли улик";
                } else {
                    yield evidence.evidenceFound;
                }
            }
            case "/end_the_day" -> {
                flagDayShift = false;
                waitingAnswer = false;
                yield endTheDay();
            }
            case "/exit" -> "Игра завершена";
            default ->  "Такой команды не существует";
        };

        return answer;
    }

    String inspect(String answer){
        int day = currentDay.ordinal() * 2;
        if (answer.equals(Puzzles.answers[day]) || answer.equals(Puzzles.answers[day + 1])){
            waitingAnswer = true;
            return Puzzles.rightDecision[currentDay.ordinal()];
        }
        return "No";
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
            }else if (character instanceof RedHerring){
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
            victim1.setStatusToDead();
        }
        else {
            victim2.setStatusToDead();
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
            dailyMessage = String.format(currentDayMessageTemplate, victim1.getFullName());
        }
        if (currentDay == Day.DAY3){
            dailyMessage = String.format(currentDayMessageTemplate, victim2.getFullName());
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

    public Victim.Status getVictim1Status(){
        return victim1.getStatus();
    }

    public Victim.Status getVictim2Status(){
        return victim2.getStatus();
    }

    public Day getCurrentDay(){
        return currentDay;
    }
}
