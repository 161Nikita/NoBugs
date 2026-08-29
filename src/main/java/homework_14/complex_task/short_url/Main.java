package homework_14.complex_task.short_url;

public class Main {
    public static void main(String[] args) {
        UrlShortenerService shortenerService = new UrlShortenerService(new ShortenerFactory(new Base62Strategy()));


        String shortUrl = shortenerService.shortenerUrl("https://example.com/very/long/url");
        System.out.println("Короткий URL: " + shortUrl);

        String longUrl = shortenerService.expendUrl(shortUrl);
        System.out.println("Длинный URL: " + longUrl);
    }
}
