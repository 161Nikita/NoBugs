package homework_14.complex_task.short_url;

public interface UrlStorage {
    void save(String shortUrl, String longUrl);
    String getLongUrl(String shortUrl);
}
