import FileExceptions.FileAccessDeniedException;
import FileExceptions.FileIsNotTXTFileException;
import FileExceptions.InvalidFilePathException;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class FrequencyDict {
    private final HashMap<Character, Integer> freqDict;

    public FrequencyDict() {
        // Инициализация словаря заглавными/строчными буквами английского алфавита
        freqDict = new HashMap<>(52);
        for (char c = 'a'; c <= 'z'; c++) {
            freqDict.put(c, 0);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            freqDict.put(c, 0);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character key : freqDict.keySet()) {
            stringBuilder.append(key).append(": ").append(freqDict.get(key)).append("\n");
        }
        return stringBuilder.toString();
    }

    // Считает частоты английских букв в тексте файла filePath
    public void calcFreqFromFile(String filePath) throws FileIsNotTXTFileException, IOException, FileAccessDeniedException, InvalidFilePathException {
        // Проверка валидности файла
        FileValidChecker.checkInputFile(filePath);

        // Подсчет частот символов английского алфавита
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                if (freqDict.containsKey((char) symbol)) {
                    freqDict.put((char) symbol, freqDict.get((char) symbol) + 1);
                }
            }
        }
    }

    public void saveToFile(String filePath) throws FileIsNotTXTFileException, IOException, FileAccessDeniedException, InvalidFilePathException {
        try {
            // Проверка валидности файла
            FileValidChecker.checkOutputFile(filePath);
        } catch (FileNotFoundException exc) {
            // В случае если файла для записи не существует, пользователю предлагается создать его
            System.out.println("Файл " + filePath + " не найден. Создать файл? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            // Не закрываем сканер, иначе вместе с ним закроется поток System.in, и сканер в мейне перестанет работать

            // Если пользователь отказался, "проталкиваем" эксепшн выше
            if (!(choice.equals("y") || choice.equals("Y"))) {
                throw exc;
            }
        }

        // Запись в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(this.toString());
        }
    }
}