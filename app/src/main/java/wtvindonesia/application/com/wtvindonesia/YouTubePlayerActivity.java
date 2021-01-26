package wtvindonesia.application.com.wtvindonesia;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import wtvindonesia.application.com.utils.Constants;

/**
 * Created by sonu on 10/11/17.
 * <p>
 * ### Here you need to extend the activity with YouTubeBaseActivity otherwise it will crash the app  ###
 */

public class YouTubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnFullscreenListener, YouTubePlayer.PlayerStateChangeListener {
    private static final String TAG = YouTubePlayerActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;
    static boolean youtube_fullscreen = false;
    static YouTubePlayer youTubePlayer;
    static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player_activity);
        context = YouTubePlayerActivity.this;

        //get the video id
        videoID = getIntent().getStringExtra("video_id");
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();
    }

    /**
     * initialize the youtube player
     */
    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(Constants.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                player.setOnFullscreenListener(YouTubePlayerActivity.this);
                player.setPlayerStateChangeListener(YouTubePlayerActivity.this);
                if (!wasRestored) {
                    youTubePlayer = player;
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.loadVideo(videoID);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }

    @Override
    public void onFullscreen(boolean b) {
        youtube_fullscreen = b;
    }

    @Override
    public void onBackPressed() {
        Log.e("BAVJ", youtube_fullscreen?"ya full":"tidak");
        if(youtube_fullscreen) {
            youTubePlayer.setFullscreen(false);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {
        youTubePlayer.seekToMillis(0);
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}
