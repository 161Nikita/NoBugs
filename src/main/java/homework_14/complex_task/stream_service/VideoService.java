package homework_14.complex_task.stream_service;

import java.util.ArrayList;
import java.util.List;

public class VideoService {
    private final List<VideoAdapter> adapters = new ArrayList<>();

    public VideoService(VideoAdapter... adapters) {
        for (VideoAdapter adapter : adapters) {
            this.adapters.add(adapter);
        }
    }
    public Video uploadVideo(String videoPath) {
        String extension = getFileExtension(videoPath);
        for (VideoAdapter adapter : adapters) {
            if (adapter.supports(extension)) {
                return adapter.convertToMp4(videoPath);
            }
        }
        throw new IllegalArgumentException("Формат не поддерживается: " + extension);
    }

    public Stream streamVideo(String videoId) {
        return new Stream(videoId);
    }
    private String getFileExtension(String path) {
        if (path != null && path.contains(".")) {
            return path.substring(path.lastIndexOf(".")+1);
        }
        return "";
    }
}
