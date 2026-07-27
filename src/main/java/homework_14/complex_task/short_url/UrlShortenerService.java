package homework_14.complex_task.short_url;

public class UrlShortenerService {
    private final UrlStorage storage;
    private final ShorteningStrategy strategy;

    public UrlShortenerService(ShortenerFactory factory) {
        this.storage = MemoriUrlStorage.getInstance();
        this.strategy = factory.createStrategy();
    }

    public String shortenerUrl(String longUrl) {
        String shortCode = strategy.shorten(longUrl);
        String finalShortUrl = "https://sh.rt" + shortCode;
        storage.save(finalShortUrl, longUrl);
        return finalShortUrl;
    }
    public String expendUrl(String shortUrl) {
        return storage.getLongUrl(shortUrl);
    }
}
