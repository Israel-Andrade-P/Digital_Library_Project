package exception;

public class InventoryEmptyException extends RuntimeException {
    public InventoryEmptyException(String message) {
        super(message);
    }
}
