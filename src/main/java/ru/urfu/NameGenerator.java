package ru.urfu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NameGenerator {

    public static void main(String[] args) throws IOException, InterruptedException {
        ApiClient apiClient = new ApiClient();
        Name[] male = apiClient.findMale().toArray(new Name[0]);
        System.out.println(Arrays.toString(male));
    }

    public String[][] generateMaleNames() {
        try {
            ApiClient apiClient = new ApiClient();
            List<Name> maleNamesList = apiClient.findMale();

            if (maleNamesList == null || maleNamesList.isEmpty()) {
                throw new IOException();
            }

            String[][] maleNames = new String[maleNamesList.size()][];

            for (int i = 0; i < maleNamesList.size(); i++) {
                maleNames[i] = maleNamesList.get(i).getNameParts();
            }

            return maleNames;

        } catch (Exception e) {
            return getBackupMaleNames();
        }
    }

    private String[][] getBackupMaleNames() {
        return new String[][] {
                {"Виктор", "Воронов"},
                {"Григорий", "Жаров"},
                {"Петр", "Воронов"},
                {"Дмитрий", "Орлов"}
        };
    }

    public String[][] generateFemaleNames() {
        try {
            ApiClient apiClient = new ApiClient();
            List<Name> femaleNamesList = apiClient.findFemale();

            if (femaleNamesList == null || femaleNamesList.isEmpty()) {
                throw new IOException();
            }

            String[][] femaleNames = new String[femaleNamesList.size()][];

            for (int i = 0; i < femaleNamesList.size(); i++) {
                femaleNames[i] = femaleNamesList.get(i).getNameParts();
            }

            return femaleNames;

        } catch (Exception e) {
            return getBackupFemaleNames();
        }
    }

    private String[][] getBackupFemaleNames() {
        return new String[][] {
                {"Лидия", "Черткова"},
                {"Анна", "Воронова"}
        };
    }
}
