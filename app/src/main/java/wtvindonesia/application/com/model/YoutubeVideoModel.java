package wtvindonesia.application.com.model;

import java.io.Serializable;

/**
 * Created by sonu on 10/11/17.
 * class to set and get the video id, title and duration for a video
 */

public class YoutubeVideoModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String videoId;
    private String title;
    private String duration;

    public YoutubeVideoModel(int id, String videoId, String title, String duration) {
        this.id       = id;
        this.videoId  = videoId;
        this.title    = title;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "YoutubeVideoModel{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
