package homework_14.complex_task.stream_service;

public class MovVideoAdapter implements VideoAdapter{
    public boolean supports(String extension) {
        return "mov".equalsIgnoreCase(extension);
    }
    public Video convertToMp4(String path) {
        System.out.println("[Adapter]: Конвертация MOV файла в MP4");
        return new Video("MP4");
    }
}
