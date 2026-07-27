package homework_14.complex_task.stream_service;

public class WmvVideoAdapter implements VideoAdapter{
    public boolean supports(String extension) {
        return "wmv".equalsIgnoreCase(extension);
    }
    public Video convertToMp4(String path) {
        System.out.println("[Adapter]: Конвертация WMV файла в MP4");
        return new Video("MP4");
    }
}
