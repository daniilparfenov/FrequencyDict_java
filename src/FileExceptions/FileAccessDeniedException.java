package FileExceptions;

public class FileAccessDeniedException extends Exception {
    public FileAccessDeniedException(String message) {
        super(message);
    }
}