package wtvindonesia.application.com.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import wtvindonesia.application.com.adapter.ListKartapediaAdapter;
import wtvindonesia.application.com.utils.Constants;
import wtvindonesia.application.com.wtvindonesia.MainActivity;
import wtvindonesia.application.com.wtvindonesia.R;
import wtvindonesia.application.com.libs.ChildAnimationExample;
import wtvindonesia.application.com.libs.CommonUtilities;
import wtvindonesia.application.com.libs.ExpandableHeightGridView;
import wtvindonesia.application.com.libs.SliderLayout;
import wtvindonesia.application.com.model.banner;

public class TabHomeFragment extends Fragment {

	private static final String TAG = MainActivity.class.getSimpleName();
	static SwipeRefreshLayout swipeRefresh;
	static SliderLayout mBannerSlider;
	static ExpandableHeightGridView gv_kategori;

	public static ProgressBar loading;
	public static LinearLayout retry;
	static Button btnReload;

	static LinearLayout label_live_tv;
	static FrameLayout youtube_frame;

	static FragmentTransaction transaction;
	static YouTubePlayerSupportFragment youTubePlayerFragment;
	public static YouTubePlayer youTubePlayer;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);

		swipeRefresh  = rootView.findViewById(R.id.swipe_container);
		mBannerSlider = rootView.findViewById(R.id.slider);

		label_live_tv = rootView.findViewById(R.id.judul_live_tv);
		youtube_frame = rootView.findViewById(R.id.frame_fragment);
		gv_kategori   = rootView.findViewById(R.id.gv_kategori);

		loading       = rootView.findViewById(R.id.pgbarLoading);
		retry         = rootView.findViewById(R.id.loadMask);
		btnReload     = rootView.findViewById(R.id.btnReload);

		swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				swipeRefresh.setRefreshing(false);
				((MainActivity) getActivity()).loadDataHome();
			}
		});

		btnReload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).loadDataHome();
			}
		});

		mBannerSlider.setPresetTransformer(SliderLayout.Transformer.Default);
		mBannerSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		mBannerSlider.setCustomAnimation(new ChildAnimationExample());
		mBannerSlider.setDuration(4000);
		mBannerSlider.addOnPageChangeListener((MainActivity) getActivity());

		youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
		transaction =  getFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_fragment, youTubePlayerFragment);
		transaction.commit();

		return rootView;
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		((MainActivity) getActivity()).loadDataHome();
	}

	public static void resultLoadDashboard(Context context) {

		for (banner data_banner : MainActivity.bannerlist) {
			TextSliderView textSliderView = new TextSliderView(context);
			textSliderView
					.bundle(new Bundle())
					.setOnSliderClickListener((MainActivity) context)
					.image(CommonUtilities.SERVER_URL+"/uploads/banner/"+data_banner.getUrl_image())
					.setScaleType(BaseSliderView.ScaleType.CenterCrop);

			textSliderView.getBundle().putInt("id", data_banner.getId_ads());
			textSliderView.getBundle().putString("nama", data_banner.getNama_ads());

			mBannerSlider.addSlider(textSliderView);
		}

		MainActivity.kategoriAdapter = new ListKartapediaAdapter(MainActivity.kategorilist);
		gv_kategori.setAdapter(MainActivity.kategoriAdapter);

		label_live_tv.setVisibility(MainActivity.liveTv_Id!=null?View.VISIBLE:View.GONE);
		youtube_frame.setVisibility(MainActivity.liveTv_Id!=null?View.VISIBLE:View.GONE);

		if(MainActivity.liveTv_Id!=null) {
			initializeYoutubePlayer(context);
		}
	}

	private static void initializeYoutubePlayer(final Context context) {

		if (youTubePlayerFragment == null) {
			return;
		}

		youTubePlayerFragment.initialize(Constants.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

			@Override
			public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
				player.setOnFullscreenListener((MainActivity) context);
				player.setPlayerStateChangeListener((MainActivity) context);
				if (!wasRestored) {
					youTubePlayer = player;
					youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
					youTubePlayer.loadVideo(MainActivity.liveTv_Id);
					youTubePlayer.play();
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
	public void onDestroy() {
		if (youTubePlayer != null) {
			youTubePlayer.release();
		}
		super.onDestroy();
	}
}
