package ru.urfu;

public class ProcessCommand {

    public String getAnswerBeforeGame(String instruction){
        String answer = "";
        switch (instruction){
            case "/help":
                answer = Strings.helpMessage;
                break;
            case "/start_game":
                answer = "Игра началась";
                break;
            case "/start":
                answer = Strings.helloMessage;
                break;

            default:
                answer = "Такой команды не существует";
        }
        return answer;
    }

}
