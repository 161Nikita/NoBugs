package homework_14.complex_task.stream_service;

public class Main {
    public static void main(String[] args) {
        VideoService videoService = new VideoService(
                new AviVideoAdapter(),
                new MovVideoAdapter(),
                new WmvVideoAdapter()
        );

        String videoPath = "path/example.avi";
        Video video = videoService.uploadVideo(videoPath);

        Stream stream = videoService.streamVideo(video.getId());
        System.out.println("Стрим " + video.getId());
    }
}
