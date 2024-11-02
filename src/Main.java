import FileExceptions.FileAccessDeniedException;
import FileExceptions.FileIsNotTXTFileException;
import FileExceptions.InvalidFilePathException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public static void main() {
    Scanner scanner = new Scanner(System.in);
    String inputFilePath = "", outputFilePath = "";
    boolean goAhead = true;

    // Main Loop
    while (goAhead) {
        // Ввод данных
        System.out.println("Введите путь до входного файла:");
        inputFilePath = scanner.nextLine();
        System.out.println("Введите путь до файла, где будет результат:");
        outputFilePath = scanner.nextLine();

        // Обработка input файла и запись результата в output файл
        FrequencyDict d = new FrequencyDict();
        try {
            System.out.println("Подсчет частот символов английского алфавита в " + inputFilePath);
            d.calcFreqFromFile(inputFilePath);
            System.out.println("Сохранение частотного словаря в " + outputFilePath);
            d.saveToFile(outputFilePath);
            System.out.println("Словарь успешно сохранен!");
        } catch (FileNotFoundException | FileAccessDeniedException | FileIsNotTXTFileException |
                 InvalidFilePathException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так");
        }

        // Запрос на выход из программы
        System.out.println("Выйти из программы? (y/n)");
        String choice = scanner.nextLine();
        goAhead = !(choice.equals("y") || choice.equals("Y"));
    }
    scanner.close();
}