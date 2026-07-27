package homework_14.complex_task.stream_service;

import java.util.UUID;

public class Video {
    private final String id;
    private final String format;
    public Video(String format) {
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.format = format;
    }

    public String getId() {
        return id;
    }

    public String getFormat() {
        return format;
    }
}
