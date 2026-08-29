package homework_14.complex_task.stream_service;

public interface VideoAdapter {
    boolean supports(String extension);
    Video convertToMp4(String path);
}
