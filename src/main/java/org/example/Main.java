package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputStr = "";

        int numberColumn = Integer.parseInt(args[0]) - 1;
        File fileCsv = new File("airports.csv");
        List<List<String>> listStr = new ArrayList<>();
        List<List<String>> listList = new ArrayList<>();
        List<List<String>> listStrFromCsvToList = new ArrayList<>();
        long time = 0;

        do {
            System.out.print("Введите строку: ");
            inputStr = scanner.nextLine().toLowerCase();
            String line;

            if ("!quit".equals(inputStr)) {
                break;
            }
            if (listStrFromCsvToList.size() == 0) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(fileCsv));

                    while ((line = br.readLine()) != null) {
                        String[] value = line.split(",");

                        ArrayList<String> arrayList = new ArrayList<>();
                        for (String inputStr2 : value) {
                            arrayList.add(inputStr2.replaceAll("\"", "").toLowerCase());
                        }

                        listStrFromCsvToList.add(Arrays.asList(value));
                        listList.add(arrayList);
                    }
                    br.close();
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
            long before = System.currentTimeMillis();
            listStr.clear();
            for (int i = 0; i < listList.size(); i++) {

                if (listList.get(i).get(numberColumn).startsWith(inputStr)) {

                    listStr.add(listStrFromCsvToList.get(i));
                }
            }
            long after = System.currentTimeMillis();
            time = after - before;
            long countStr = listStr.size();
            for (List<String> strings : listStr) {
                System.out.println(strings.get(numberColumn).replaceAll("\"", "") + " " + strings);
            }
            System.out.println();
            System.out.println("Время поиска: " + time + "ms." + " Количество строк: " + countStr);
            System.out.println();
        } while (!"!quit".equals(inputStr));
    }
}
