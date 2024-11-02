import FileExceptions.FileAccessDeniedException;
import FileExceptions.FileIsNotTXTFileException;
import FileExceptions.InvalidFilePathException;

import java.io.File;
import java.io.FileNotFoundException;

// Проверяет валидность input/output файлов
public class FileValidChecker {
    private static void checkFile(String filePath) throws FileNotFoundException, FileIsNotTXTFileException, InvalidFilePathException {
        File file = new File(filePath);

        if (filePath == null || filePath.trim().isEmpty()) {
            throw new InvalidFilePathException("Путь до файла не может быть пустым");
        }

        // Проверка существования файла
        if (!file.exists()) {
            throw new FileNotFoundException("Файл " + filePath + " не найден!");
        }

        // Проверка, что файл действительно является файлом (а не директорией, например) и что формат файла - txt
        if (!file.isFile() || !filePath.endsWith(".txt")) {
            throw new FileIsNotTXTFileException("Файл " + filePath + " не является файлом в формате txt");
        }
    }

    public static void checkInputFile(String filePath) throws FileIsNotTXTFileException, FileNotFoundException, FileAccessDeniedException, InvalidFilePathException {
        checkFile(filePath);
        File file = new File(filePath);
        // Проверка на право чтения файла
        if (!file.canRead()) {
            throw new FileAccessDeniedException("Файл " + filePath + " не доступен для чтения");
        }
    }

    public static void checkOutputFile(String filePath) throws FileIsNotTXTFileException, FileNotFoundException, FileAccessDeniedException, InvalidFilePathException {
        checkFile(filePath);
        File file = new File(filePath);
        // Проверка на право записи в файл
        if (!file.canWrite()) {
            throw new FileAccessDeniedException("Файл " + filePath + " не доступен для записи");
        }
    }


}