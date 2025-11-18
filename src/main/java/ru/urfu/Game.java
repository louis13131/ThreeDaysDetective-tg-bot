package ru.urfu;

public class Game {
    private Killer lidiaChertkova;
    private Victim dmitriyOrlov;
    private Victim annaVoronova;
    private Victim petrVoronov;
    private Victim grigoriyZharov;

    private Day currentDay;

    private boolean isRunning = false;

    public Game(){
        lidiaChertkova = new Killer(Strings.lidia);
        dmitriyOrlov = new Victim(Strings.dmitriy);
        annaVoronova = new Victim(Strings.anna);
        petrVoronov = new Victim(Strings.petr);
        grigoriyZharov = new Victim(Strings.grigoriy);
        currentDay = Day.DAY1;
    }

    private String[] stringParsing(String instruction){
        return instruction.trim().split(" ", 2);
    }

    public String processCommandInGame(String instruction){
        String answer;
        switch (instruction) {
            case "/info_lidia":
                answer = lidiaChertkova.getInfo();
                break;
            case "/info_dmitriy":
                answer = dmitriyOrlov.getInfo();
                break;
            case "/info_anna":
                answer = annaVoronova.getInfo();
                break;
            case "/info_petr":
                answer = petrVoronov.getInfo();
                break;
            case "/info_grigoriy":
                answer = grigoriyZharov.getInfo();
                break;
            case "/help":
                answer = Strings.helpMessage;
                break;
            case "/start_game":
                answer = "Игра уже началась";
                break;
            case "/talk_lidia":
                answer = Strings.lidiaDialoguesByDay[currentDay.ordinal()];
                break;
            case "/talk_dmitriy":
                answer = Strings.dmitriyDialoguesByDay[currentDay.ordinal()];
                break;
            case "/talk_anna":
                answer = Strings.annaDialoguesByDay[currentDay.ordinal()];
                break;
            case "/talk_petr":
                if(petrVoronov.getStatus() == Victim.Status.ALIVE){
                    answer = Strings.petrDialoguesByDay[currentDay.ordinal()];
                }
                else answer = Strings.deathMessage;
                break;
            case "/talk_grigoriy":
                if(grigoriyZharov.getStatus() == Victim.Status.ALIVE){
                    answer = Strings.grigoriyDialoguesByDay[currentDay.ordinal()];
                }
                else answer = Strings.deathMessage;
                break;
            case "/end_the_day":
                answer = endTheDay();
                break;
            case "/exit":
                answer = "Игра завершена";
                break;
            case "/blame_lidia": //Специально проваливаемся вниз, чтобы попасть в default при невыполнении условия
                if (currentDay == Day.DAY3) {
                    answer = Strings.victoryMessage;
                    break;
                }
            case "/blame_dmitriy", "/blame_anna":
                if (currentDay == Day.DAY3) {
                    answer = Strings.defeatMessage;
                    break;
                }

            default:
                answer = "Такой команды не существует";
        }
        return answer;
    }

    public String endTheDay(){
        if (currentDay == Day.DAY3){
            return "Это последний день";
        }

        String answer = Strings.dailyMessage[currentDay.ordinal()];
        currentDay = currentDay.next();

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
}
