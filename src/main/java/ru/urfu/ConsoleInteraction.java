package ru.urfu;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ConsoleInteraction {
    private Scanner scanner;

    public ConsoleInteraction() {
        scanner = new Scanner(System.in);
    }

    public String getCommand(){
        return scanner.nextLine();
    }

    public void print(String text){
        System.out.println(text);
    }
}