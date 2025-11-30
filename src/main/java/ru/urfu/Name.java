package ru.urfu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Name(String title, String first, String last){
    public String[] getNameParts(){
        String[] fullName = new String[2];
        fullName[0] = first;
        fullName[1] = last;
        return fullName;
    }
}
