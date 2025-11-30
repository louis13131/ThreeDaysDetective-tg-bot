package ru.urfu;

import java.io.IOException;

public class Game {
    private Killer lidiaChertkova;
    private Victim dmitriyOrlov;
    private Victim annaVoronova;
    private Victim petrVoronov;
    private Victim grigoriyZharov;
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

        lidiaChertkova = new Killer(femaleNames[0], Strings.lidia);
        dmitriyOrlov = new Victim(maleNames[1], Strings.dmitriy);
        annaVoronova = new Victim(familyFemaleName, Strings.anna);
        petrVoronov = new Victim(familyMaleName, Strings.petr);
        grigoriyZharov = new Victim(maleNames[3], Strings.grigoriy);
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
            answer = processCharacterCommand(prevCommand, instruction);
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

    String processCharacterCommand(String instruction, String name){
        String answer = switch(instruction){
            case "/info" -> infoAboutCharacter(name);
            case "/talk" -> talkToCharacter(name);
            case "/blame" -> blameCharacter(name);
            default -> "Такого персонажа не существует";
        };

        return answer;
    }

    public String infoAboutCharacter(String name) {
        String answer = switch (name) {
            case "lidia", "Lidia" -> lidiaChertkova.getInfo();
            case "dmitriy", "Dmitriy" -> dmitriyOrlov.getInfo();
            case "anna", "Anna" -> annaVoronova.getInfo();
            case "petr", "Petr" -> petrVoronov.getInfo();
            case "grigoriy", "Grigoriy" -> grigoriyZharov.getInfo();
            default -> "Такого персонажа не существует";
        };
        return answer;
    }

    public String talkToCharacter(String name){
        String answer = switch (name) {
            case "lidia", "Lidia" -> Strings.lidiaDialoguesByDay[currentDay.ordinal()];
            case "dmitriy", "Dmitriy" -> Strings.dmitriyDialoguesByDay[currentDay.ordinal()];
            case "anna", "Anna" -> Strings.annaDialoguesByDay[currentDay.ordinal()];
            case "petr", "Petr" -> {
                if(petrVoronov.getStatus() == Victim.Status.ALIVE){
                    yield Strings.petrDialoguesByDay[currentDay.ordinal()];
                }
                yield Strings.deathMessage;
            }
            case "grigoriy", "Grigoriy" -> {
                if(grigoriyZharov.getStatus() == Victim.Status.ALIVE){
                    yield Strings.grigoriyDialoguesByDay[currentDay.ordinal()];
                }
                yield Strings.deathMessage;
            }
            default -> "Такого персонажа не существует";
        };
        return answer;
    }

    public String blameCharacter(String name){
        if (currentDay == Day.DAY3){
            String answer = switch (name){
                case "lidia", "Lidia" -> Strings.victoryMessage;
                case "dmitriy", "Dmitriy" -> Strings.defeatMessage;
                case "anna", "Anna" -> Strings.defeatMessage;
                case "petr", "Petr", "grigoriy", "Grigoriy" -> "Этот персонаж мёртв, его нельзя обвинить";
                default -> "Такого персонажа не существует";
            };
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

        answer = Strings.dailyMessage[currentDay.ordinal()-1];

        if  (currentDay == Day.DAY2){
            grigoriyZharov.setStatusToDead();
        }
        else {
            petrVoronov.setStatusToDead();
        }

        return answer;
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
