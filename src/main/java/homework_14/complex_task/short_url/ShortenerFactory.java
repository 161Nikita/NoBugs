package homework_14.complex_task.short_url;

public class ShortenerFactory {
    private final ShorteningStrategy strategy;

    public ShortenerFactory(ShorteningStrategy strategy) {
        this.strategy = strategy;
    }
    public ShorteningStrategy createStrategy() {
        return this.strategy;
    }
}
