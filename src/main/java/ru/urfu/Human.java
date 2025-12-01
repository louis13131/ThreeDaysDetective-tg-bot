package ru.urfu;


public abstract class Human {
    private final String name;
    private final String surname;
    private final String age;
    private final String profession;
    private final String impression;
    private final String reason;

    protected Human(String[] nameParts, String[] character) {
        this.name = nameParts[0];
        this.surname = nameParts[1];
        this.age = character[0];
        this.profession = character[1];
        this.impression = character[2];
        this.reason = character[3];
    }

    public String getInfo(){
        return String.format("--- Информация о %s %s --- \n%s лет \nРод занятий: %s \nОписание: %s \nМотив: %s", name, surname, age, profession, impression, reason);
    }

    public String getName(){
        return name;
    }
    public String getFullName(){
        String fullName = name + " " + surname;
        return fullName;
    }
}
