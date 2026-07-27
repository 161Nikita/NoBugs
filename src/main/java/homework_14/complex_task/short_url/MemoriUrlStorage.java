package homework_14.complex_task.short_url;

import java.util.HashMap;
import java.util.Map;

public class MemoriUrlStorage implements UrlStorage {
    private final Map<String, String> storage = new HashMap<>();
    private static MemoriUrlStorage instance;

    private MemoriUrlStorage() {
    }

    public static MemoriUrlStorage getInstance() {
        if (instance == null) {
            instance = new MemoriUrlStorage();
        }
        return instance;
    }

    @Override
    public void save(String shortUrl, String longUrl) {
        storage.put(shortUrl, longUrl);
    }

    @Override
    public String getLongUrl(String shortUrl) {
        return storage.get(shortUrl);
    }
}
