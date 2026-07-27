package homework_14.complex_task.stream_service;

public class AviVideoAdapter implements VideoAdapter{
    public boolean supports(String extension) {
        return "avi".equalsIgnoreCase(extension);
    }
    public Video convertToMp4(String path){
        System.out.println("[Adapter]: Конвертация AVI файла в MP4");
        return new Video("MP4");
    }
}
