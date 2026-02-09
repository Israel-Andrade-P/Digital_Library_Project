package util;

import java.util.Random;
import java.util.function.Supplier;

public class BookUtils {
    public static Supplier<String> generateId = () -> {
        String pool = "1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randIdx = random.nextInt(pool.length());
            sb.append(pool.charAt(randIdx));
        }
        return sb.toString();
    };
}
