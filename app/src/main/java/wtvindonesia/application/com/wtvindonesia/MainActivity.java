package wtvindonesia.application.com.wtvindonesia;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.google.android.youtube.player.YouTubePlayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import customfonts.MyTextView;
import wtvindonesia.application.com.adapter.ListKartapediaAdapter;
import wtvindonesia.application.com.adapter.MoreMenuAdapter;
import wtvindonesia.application.com.adapter.YoutubeVideoAdapter;
import wtvindonesia.application.com.fragment.KategoriFragment;
import wtvindonesia.application.com.fragment.TabHomeFragment;
import wtvindonesia.application.com.fragment.TabMoviesFragment;
import wtvindonesia.application.com.fragment.TabTvShowFragment;
import wtvindonesia.application.com.libs.CommonUtilities;
import wtvindonesia.application.com.libs.GalleryFilePath;
import wtvindonesia.application.com.libs.JSONParser;
import wtvindonesia.application.com.libs.ServerUtilities;
import wtvindonesia.application.com.model.YoutubeVideoModel;
import wtvindonesia.application.com.model.YoutubeVideoModel_list;
import wtvindonesia.application.com.model.kategori;
import wtvindonesia.application.com.model.moremenu;
import wtvindonesia.application.com.model.user;
import wtvindonesia.application.com.fragment.DashboardFragment;
import wtvindonesia.application.com.model.banner;

import static wtvindonesia.application.com.libs.CommonUtilities.getOptionsImage;
import static wtvindonesia.application.com.libs.CommonUtilities.initImageLoader;
import com.soundcloud.android.crop.Crop;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, NavigationView.OnNavigationItemSelectedListener, YouTubePlayer.OnFullscreenListener, YouTubePlayer.PlayerStateChangeListener {

	final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
	public static boolean youtube_fullscreen = false;
	
	final int REQUEST_FROM_GALLERY = 1;
	final int REQUEST_FROM_CAMERA  = 2;

	public static Context context;
	public static user data;

	//BANNER
	public static ArrayList<banner> bannerlist = new ArrayList<>();

	//VIDEO ID LIVE TV
	public static String liveTv_Id;

	//KATEGORI
	public static ArrayList<kategori> kategorilist = new ArrayList<>();
	public static ListKartapediaAdapter kategoriAdapter;

	// LIVE TV LIST
	int next_page_tv_show;
	public static ArrayList<YoutubeVideoModel> tvshowlist = new ArrayList<>();
	public static YoutubeVideoAdapter tvshowAdapter;

    //MOVIES LIST
    int next_page_movies;
    public static ArrayList<YoutubeVideoModel> movieslist = new ArrayList<>();
    public static YoutubeVideoAdapter moviesAdapter;

	moremenu vidkategori;
	int page_video_kategori;
	ArrayList<YoutubeVideoModel> videokategorilist = new ArrayList<>();
	public static YoutubeVideoAdapter videokategoriAdapter;

	static int menu_selected = 0;

	public static ImageLoader imageLoader;
	public static DisplayImageOptions imageOption;

	ImageView menu;
	Toolbar toolbar;
	DrawerLayout drawer;
	LinearLayout mDrawerPane;

	ImageView avatar;

	//SLIDE MENU
	ArrayList<moremenu> moremenulist = new ArrayList<>();
	Map<moremenu, ArrayList<moremenu>> submoremenulist = new LinkedHashMap<>();
	MoreMenuAdapter moremenuAdapter;
	public static ExpandableListView moremenuListView;

	Dialog dialog_loading;

	Dialog dialog_informasi;
	MyTextView btn_ok;
	MyTextView text_title;
	MyTextView text_informasi;

	Dialog dialog_pilih_gambar;
	MyTextView from_camera, from_galery;

	private static Uri mImageCaptureUri;

	int count_close = 1;
	int current_click = 0;
	Handler mHandlerClose = new Handler();
	Handler mHandlerDisplayView = new Handler();

	public static moremenu moremenu_select = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (Build.VERSION.SDK_INT >= 23) {
			insertDummyContactWrapper();
		}

		if (Build.VERSION.SDK_INT >= 21) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.parseColor("#242526"));
		}

		context = MainActivity.this;
		data = CommonUtilities.getSettingUser(context);

		toolbar = findViewById(R.id.toolbar);
		menu = findViewById(R.id.menu);
		drawer = findViewById(R.id.drawer_layout);
		mDrawerPane = findViewById(R.id.drawerPane);

		int width = getResources().getDisplayMetrics().widthPixels;
		width = width - (width / 3);
		DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mDrawerPane.getLayoutParams();
		params.width = width;
		mDrawerPane.setLayoutParams(params);

		moremenuListView = findViewById(R.id.moremenulistview);

		menu.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View view) {
				if (drawer.isDrawerOpen(GravityCompat.START)) {
					drawer.closeDrawer(GravityCompat.START);
				} else {
					drawer.openDrawer(GravityCompat.START);
				}
			}
		});

		avatar = findViewById(R.id.banar1);

		moremenuAdapter = new MoreMenuAdapter(context, moremenulist, submoremenulist);
		moremenuListView.setAdapter(moremenuAdapter);

		moremenuListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				vidkategori = moremenulist.get(groupPosition);
				displayView(1);

				return false;
			}
		});

		avatar.setLayerType(View.LAYER_TYPE_HARDWARE, null);

		dialog_loading = new Dialog(context);
		dialog_loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_loading.setCancelable(false);
		dialog_loading.setContentView(R.layout.loading_dialog);

		dialog_informasi = new Dialog(context);
		dialog_informasi.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_informasi.setCancelable(true);
		dialog_informasi.setContentView(R.layout.informasi_dialog);

		btn_ok = dialog_informasi.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog_informasi.dismiss();
			}
		});

		text_title = dialog_informasi.findViewById(R.id.text_title);
		text_informasi = dialog_informasi.findViewById(R.id.text_dialog);

		initImageLoader(context);
		imageLoader = ImageLoader.getInstance();
		imageOption = getOptionsImage(R.drawable.userdefault, R.drawable.userdefault);
		
		menu_selected = 0;

		if(savedInstanceState==null) {
			//checkGcmRegid();
			menu_selected = getIntent().getIntExtra("menu_select", 0);
		}
		setSignIn();
		
		dialog_pilih_gambar = new Dialog(context);
		dialog_pilih_gambar.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_pilih_gambar.setCancelable(true);
		dialog_pilih_gambar.setContentView(R.layout.pilih_gambar_dialog);

		from_galery = dialog_pilih_gambar.findViewById(R.id.txtFromGalley);
		from_galery.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_pilih_gambar.dismiss();
				fromGallery();
			}
		});

		from_camera = dialog_pilih_gambar.findViewById(R.id.txtFromCamera);
		from_camera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_pilih_gambar.dismiss();
				fromCamera();
			}
		});
		
	}

	public void selectImage() {
		dialog_pilih_gambar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog_pilih_gambar.show();
	}

	private void beginCrop(Uri source) {
		Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
		Crop.of(source, destination).asSquare().start(this);
	}

	private void fromGallery() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, REQUEST_FROM_GALLERY);
	}

	private void fromCamera() {

		Intent intent = new Intent(context, AmbilFotoActivity.class);
		startActivityForResult(intent, REQUEST_FROM_CAMERA);
	}

	@Override
	public void onFullscreen(boolean b) {
		MainActivity.youtube_fullscreen = b;
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
		TabHomeFragment.youTubePlayer.seekToMillis(0);
	}

	@Override
	public void onError(YouTubePlayer.ErrorReason errorReason) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (menu_selected == 0) {
				if (current_click == count_close) {
					//Toast.makeText(context, DashboardFragment.pager.getCurrentItem()+ "", Toast.LENGTH_LONG).show();
					finish();
				} else {
					current_click++;

					Toast.makeText(context, "Tekan dua kali untuk keluar.", Toast.LENGTH_SHORT).show();
					mHandlerClose.postDelayed(mUpdateTimeTask, 1000);

					return false;
				}
			}

			if (menu_selected > 0) {
				current_click=0;
				displayView(0);

				return false;
			}
		}

		return super.onKeyDown(keyCode, event);
	}

	public void displayView(int position) {
		drawer.closeDrawer(GravityCompat.START);

		menu_selected = position;
		Fragment fragment = null;

		switch (position) {
			case 0:
				fragment = new DashboardFragment();
				break;

			case 1:
				openDetailCategory(new kategori(vidkategori.getId(), vidkategori.getNama(), false));
				//fragment = new KategoriFragment();
				break;

			default:
				break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
		}
	}

	@Override
	protected void onDestroy() {
		try {
			unregisterReceiver(mHandleLoadDataHomeReceiver);
			unregisterReceiver(mHandleLoadDataTvShowReceiver);
			unregisterReceiver(mHandleLoadDataMoviesReceiver);
			unregisterReceiver(mHandleLoadDataNewsReceiver);
			unregisterReceiver(mHandleLoadDataVideoKategoriReceiver);

			mHandlerClose.removeCallbacks(mUpdateTimeTask);
			mHandlerDisplayView.removeCallbacks(mDisplayViewTask);
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		try {
			unregisterReceiver(mHandleLoadDataHomeReceiver);
			unregisterReceiver(mHandleLoadDataTvShowReceiver);
			unregisterReceiver(mHandleLoadDataMoviesReceiver);
			unregisterReceiver(mHandleLoadDataNewsReceiver);
			unregisterReceiver(mHandleLoadDataVideoKategoriReceiver);

			mHandlerClose.removeCallbacks(mUpdateTimeTask);
			mHandlerDisplayView.removeCallbacks(mDisplayViewTask);
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.onPause();
	}

	@Override
	protected void onResume() {
		registerReceiver(mHandleLoadDataHomeReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_HOME"));
		registerReceiver(mHandleLoadDataTvShowReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_TV_SHOW"));
		registerReceiver(mHandleLoadDataMoviesReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_MOVIES"));
		registerReceiver(mHandleLoadDataNewsReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_NEWS"));
		registerReceiver(mHandleLoadDataVideoKategoriReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_VIDEO_KATEGORI"));

		super.onResume();
	}


	private final BroadcastReceiver mHandleLoadDataHomeReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context cntx, Intent intent) {
			Boolean success = intent.getBooleanExtra("success", false);

			TabHomeFragment.loading.setVisibility(View.GONE);
			if(success) {
				moremenuAdapter.UpdateMoreMenuAdapter(moremenulist, submoremenulist);
				TabHomeFragment.resultLoadDashboard(context);
			} else {
				TabHomeFragment.retry.setVisibility(View.VISIBLE);
			}

		}
	};

	private final BroadcastReceiver mHandleLoadDataTvShowReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			Boolean success = intent.getBooleanExtra("success", false);
			TabTvShowFragment.loading.setVisibility(View.GONE);
			if(success) {
				ArrayList<YoutubeVideoModel_list> temp = intent.getParcelableArrayListExtra("data_tvshow_list");
				ArrayList<YoutubeVideoModel> result = temp.get(0).getListData();
				if(result.size()>0) {
					for (YoutubeVideoModel flist : result) {
						tvshowlist.add(flist);
					}
					tvshowAdapter.updateAdapter(tvshowlist);
				}
			} else {
				TabTvShowFragment.retry.setVisibility(View.VISIBLE);
			}

		}
	};

	private final BroadcastReceiver mHandleLoadDataMoviesReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			Boolean success = intent.getBooleanExtra("success", false);
			TabMoviesFragment.loading.setVisibility(View.GONE);
			if(success) {
				ArrayList<YoutubeVideoModel_list> temp = intent.getParcelableArrayListExtra("data_movies_list");
				ArrayList<YoutubeVideoModel> result = temp.get(0).getListData();
				if(result.size()>0) {
					for (YoutubeVideoModel flist : result) {
						movieslist.add(flist);
					}
					moviesAdapter.updateAdapter(movieslist);
				}
			} else {
				TabMoviesFragment.retry.setVisibility(View.VISIBLE);
			}

		}
	};

	private final BroadcastReceiver mHandleLoadDataNewsReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			Boolean success = intent.getBooleanExtra("success", false);
			TabTvShowFragment.loading.setVisibility(View.GONE);
			if(success) {
				ArrayList<YoutubeVideoModel_list> temp = intent.getParcelableArrayListExtra("data_tvshow_list");
				ArrayList<YoutubeVideoModel> result = temp.get(0).getListData();
				if(result.size()>0) {
					for (YoutubeVideoModel flist : result) {
						tvshowlist.add(flist);
					}
					tvshowAdapter.updateAdapter(tvshowlist);
				}
			} else {
				TabTvShowFragment.retry.setVisibility(View.VISIBLE);
			}

		}
	};

	private final BroadcastReceiver mHandleLoadDataVideoKategoriReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			Boolean success = intent.getBooleanExtra("success", false);
			KategoriFragment.loading.setVisibility(View.GONE);
			if(success) {
				ArrayList<YoutubeVideoModel_list> temp = intent.getParcelableArrayListExtra("data_videos_list");
				ArrayList<YoutubeVideoModel> result = temp.get(0).getListData();
				if(result.size()>0) {
					for (YoutubeVideoModel flist : result) {
						videokategorilist.add(flist);
					}
					videokategoriAdapter.updateAdapter(videokategorilist);
				}

				if(videokategorilist.size()==0) {
					KategoriFragment.textView2.setVisibility(View.VISIBLE);
				}
			} else {
				KategoriFragment.retry.setVisibility(View.VISIBLE);
			}
		}
	};

	public void openDialogMessage(String message, boolean status) {
		text_informasi.setText(message);
		text_title.setText(status?"BERHASIL":"KESALAHAN");
		dialog_informasi.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog_informasi.show();
	}

	public void openDetailCategory(kategori kat) {
		Intent i = new Intent(context, YouTubeCategoryActivity.class);
		i.putExtra("category", kat);
		startActivity(i);

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);

		savedInstanceState.putInt("menu_selected", menu_selected);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		menu_selected = savedInstanceState.getInt("menu_selected");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data_intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data_intent);

		String fileName = new SimpleDateFormat("yyyyMMddhhmmss'.jpg'").format(new Date());
		String dest = CommonUtilities.getOutputPath(context, "images")+File.separator+fileName;

		if (resultCode == RESULT_OK) {
			switch (requestCode) {

				case Crop.REQUEST_CROP: {
					mImageCaptureUri = Crop.getOutput(data_intent);

					break;
				}
				case REQUEST_FROM_CAMERA: {
					CommonUtilities.compressImage(context, data_intent.getStringExtra("path"), dest);
					mImageCaptureUri = Uri.fromFile(new File(dest));
					beginCrop(mImageCaptureUri);

					break;
				}
				case REQUEST_FROM_GALLERY: {
					Uri selectedUri = data_intent.getData();
					CommonUtilities.compressImage(context, GalleryFilePath.getPath(context, selectedUri), dest);
					mImageCaptureUri = Uri.fromFile(new File(dest));
					beginCrop(mImageCaptureUri);

					break;
				}
			}
		}
	}

	public void loadDataHome() {
		new prosesLoadDataHome().execute();
	}

	public class prosesLoadDataHome extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			TabHomeFragment.loading.setVisibility(View.VISIBLE);
			TabHomeFragment.retry.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(String... urls) {

			Boolean success = false;
			bannerlist.clear();
			kategorilist.clear();
			liveTv_Id = null;

			String url = CommonUtilities.SERVER_URL + "/store/androidHomeStore.php";
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("id_user", data.getId()+""));
			JSONObject json = new JSONParser().getJSONFromUrl(url, params, null);

			if (json != null) {
				try {

					//BANNER
					JSONArray banner = json.isNull("banner") ? null : json.getJSONArray("banner");
					for (int i = 0; i < banner.length(); i++) {
						JSONObject rec_banner = banner.getJSONObject(i);
						int id = rec_banner.isNull("id") ? null : rec_banner.getInt("id");
						String nama = rec_banner.isNull("nama") ? null : rec_banner.getString("nama");
						String ktg = rec_banner.isNull("kategori") ? null : rec_banner.getString("kategori");
						String bnr = rec_banner.isNull("banner") ? null : rec_banner.getString("banner");
						bannerlist.add(new banner(id, nama, ktg, bnr));
					}

					//VIDEO ID LIVE TV
					liveTv_Id = json.isNull("live_tv_video_id") ? null : json.getString("live_tv_video_id");

					//KATEGORI
					JSONArray kategori = json.isNull("kategori")?null:json.getJSONArray("kategori");
					for (int i=0; i<kategori.length(); i++) {
						JSONObject rec_kategori = kategori.getJSONObject(i);

						int id = rec_kategori.isNull("id")?null:rec_kategori.getInt("id");
						String nama = rec_kategori.isNull("nama")?null:rec_kategori.getString("nama");
						String penjelasan = rec_kategori.isNull("penjelasan")?null:rec_kategori.getString("penjelasan");
						String header = rec_kategori.isNull("header")?null:rec_kategori.getString("header");
						kategorilist.add(new kategori(id, nama, penjelasan, header));
						moremenulist.add(new moremenu(id, id+"", nama, header, R.drawable.menu2));
					}

					success = true;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Intent i = new Intent("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_HOME");
			i.putExtra("success", success);
			sendBroadcast(i);

			return null;
		}
	}

    public void loadDataTvShow(boolean first) {
		if(first) {
			next_page_tv_show = 1;
			tvshowlist .clear();
			tvshowAdapter = new YoutubeVideoAdapter(context, tvshowlist);
			TabTvShowFragment.recyclerView.setAdapter(tvshowAdapter);
			TabTvShowFragment.scrollListener.resetState();
		}
        new prosesLoadDataTvShow().execute();
    }

    public class prosesLoadDataTvShow extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

			TabTvShowFragment.loading.setVisibility(View.VISIBLE);
			TabTvShowFragment.retry.setVisibility(View.GONE);
		}

        @Override
        protected Void doInBackground(String... urls) {

            ArrayList<YoutubeVideoModel> result = null;
            String url = CommonUtilities.SERVER_URL + "/store/androidVideoStore.php";

            List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("page", next_page_tv_show+""));
			params.add(new BasicNameValuePair("id_grup", "1"));

            JSONObject json = new JSONParser().getJSONFromUrl(url, params, null);
            if(json!=null) {
                try {
                    result = new ArrayList<>();
					next_page_tv_show = json.isNull("next_page") ? next_page_tv_show : json.getInt("next_page");
                    JSONArray topics = json.isNull("topics")?null:json.getJSONArray("topics");
                    for (int i=0; i<topics.length(); i++) {
                        JSONObject rec = topics.getJSONObject(i);

                        int id          = rec.isNull("id")?0:rec.getInt("id");
                        String videoId  = rec.isNull("videoId")?"":rec.getString("videoId");
                        String title    = rec.isNull("title")?"":rec.getString("title");
                        String duration = rec.isNull("duration")?"":rec.getString("duration");

                        result.add(new YoutubeVideoModel(id, videoId, title, duration));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            Boolean success = result!=null;
            if(result==null) result = new ArrayList<>();
            ArrayList<YoutubeVideoModel_list> temp = new ArrayList<>();
            temp.add(new YoutubeVideoModel_list(result));

            Intent i = new Intent("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_TV_SHOW");
            i.putExtra("data_tvshow_list", temp);
            i.putExtra("success", success);
            sendBroadcast(i);

            return null;
        }
    }

	public void loadDataMovies(boolean first) {
		if(first) {
			next_page_movies = 1;
			movieslist .clear();
			moviesAdapter = new YoutubeVideoAdapter(context, movieslist);
			TabMoviesFragment.recyclerView.setAdapter(moviesAdapter);
			TabMoviesFragment.scrollListener.resetState();
		}
		new prosesLoadDataMovies().execute();
	}

	public class prosesLoadDataMovies extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			TabMoviesFragment.loading.setVisibility(View.VISIBLE);
			TabMoviesFragment.retry.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(String... urls) {

			ArrayList<YoutubeVideoModel> result = null;
			String url = CommonUtilities.SERVER_URL + "/store/androidVideoStore.php";

			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("page", next_page_movies+""));
			params.add(new BasicNameValuePair("id_grup", "2"));

			JSONObject json = new JSONParser().getJSONFromUrl(url, params, null);
			if(json!=null) {
				try {
					result = new ArrayList<>();
					next_page_movies = json.isNull("next_page") ? next_page_movies : json.getInt("next_page");
					JSONArray topics = json.isNull("topics")?null:json.getJSONArray("topics");
					for (int i=0; i<topics.length(); i++) {
						JSONObject rec = topics.getJSONObject(i);

						int id          = rec.isNull("id")?0:rec.getInt("id");
						String videoId  = rec.isNull("videoId")?"":rec.getString("videoId");
						String title    = rec.isNull("title")?"":rec.getString("title");
						String duration = rec.isNull("duration")?"":rec.getString("duration");

						result.add(new YoutubeVideoModel(id, videoId, title, duration));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Boolean success = result!=null;
			if(result==null) result = new ArrayList<>();
			ArrayList<YoutubeVideoModel_list> temp = new ArrayList<>();
			temp.add(new YoutubeVideoModel_list(result));

			Intent i = new Intent("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_TAB_MOVIES");
			i.putExtra("data_movies_list", temp);
			i.putExtra("success", success);
			sendBroadcast(i);

			return null;
		}
	}

	public void loadDataVideoKetegori(boolean first) {
		if(first) {
			page_video_kategori = 1;
			videokategorilist.clear();
			videokategoriAdapter = new YoutubeVideoAdapter(context, videokategorilist);
			KategoriFragment.recyclerView.setAdapter(videokategoriAdapter);
			KategoriFragment.scrollListener.resetState();
			KategoriFragment.title.setText(vidkategori.getNama());
		}

		new prosesLoadDataVideoKetegori().execute();
	}

	public class prosesLoadDataVideoKetegori extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			KategoriFragment.textView2.setVisibility(View.GONE);
			KategoriFragment.loading.setVisibility(View.VISIBLE);
			KategoriFragment.retry.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(String... urls) {

			ArrayList<YoutubeVideoModel> result = null;
			String url = CommonUtilities.SERVER_URL + "/store/androidVideoCategoryStore.php";

			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("page", page_video_kategori+""));
			params.add(new BasicNameValuePair("category", vidkategori.getKode()));

			JSONObject json = new JSONParser().getJSONFromUrl(url, params, null);
			if(json!=null) {
				try {
					result = new ArrayList<>();
					page_video_kategori = json.isNull("next_page") ? page_video_kategori : json.getInt("next_page");
					JSONArray topics = json.isNull("topics")?null:json.getJSONArray("topics");
					for (int i=0; i<topics.length(); i++) {
						JSONObject rec = topics.getJSONObject(i);

						int id          = rec.isNull("id")?0:rec.getInt("id");
						String videoId  = rec.isNull("videoId")?"":rec.getString("videoId");
						String title    = rec.isNull("title")?"":rec.getString("title");
						String duration = rec.isNull("duration")?"":rec.getString("duration");

						result.add(new YoutubeVideoModel(id, videoId, title, duration));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Boolean success = result!=null;
			if(result==null) result = new ArrayList<>();
			ArrayList<YoutubeVideoModel_list> temp = new ArrayList<>();
			temp.add(new YoutubeVideoModel_list(result));

			Intent i = new Intent("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_VIDEO_KATEGORI");
			i.putExtra("data_videos_list", temp);
			i.putExtra("success", success);
			sendBroadcast(i);

			return null;
		}
	}

	public void openDetailVideoKategori(int pos) {
		Intent i = new Intent(context, YouTubePlayerActivity.class);
		i.putExtra("video_id", videokategorilist.get(pos).getId()+"");
		startActivity(i);
	}


	@Override
	public void onSliderClick(BaseSliderView slider) {
		//Toast.makeText(context,slider.getBundle().getInt("id") + "  " + slider.getBundle().getString("nama"),Toast.LENGTH_SHORT).show();

		openDetailBanner(slider.getBundle().getInt("id"), slider.getBundle().getString("nama"));

	}

	@Override
	public void onBackPressed() {
		Log.e("BAVJ", youtube_fullscreen?"ya full":"tidak");
		if(!youtube_fullscreen) {
			DrawerLayout drawer = findViewById(R.id.drawer_layout);

			if (drawer.isDrawerOpen(GravityCompat.START)) {
				drawer.closeDrawer(GravityCompat.START);
			} else {
				super.onBackPressed();
			}
		} else {
			TabHomeFragment.youTubePlayer.setFullscreen(false);
		}

	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	public void openDetailBanner(int banner_id, String banner_title) {
		Intent i = new Intent(context, YouTubeCategoryActivity.class);
		i.putExtra("category", new kategori(banner_id, banner_title, false));
		i.putExtra("is_banner", true);

		startActivity(i);
	}

	public void openDetailNews(int news_id, String news_title) {
		Intent i = new Intent(context, YouTubeCategoryActivity.class);
		i.putExtra("category", new kategori(news_id, news_title, false));
		i.putExtra("is_news", true);

		startActivity(i);
	}


	public void openDetailVideo(String  videoId) {
		Intent i = new Intent(context, YouTubePlayerActivity.class);
		i.putExtra("video_id", videoId);
		startActivity(i);

		/*Intent standAlonePlayerIntent = YouTubeStandalonePlayer.createVideoIntent(MainActivity.this,
				Constants.DEVELOPER_KEY, // which you have created in step 3
				videoId, // video which is to be played
				100,     //The time, in milliseconds, where playback should start in the video
				true,    //autoplay or not
				false);   //lightbox mode or not; false will show in fullscreen
		context.startActivity(standAlonePlayerIntent);*/
	}

	public void openDialogLoading(boolean cancelable) {
		dialog_loading.setCancelable(cancelable);
		dialog_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog_loading.show();
	}

	private void setSignIn() {
		avatar.setImageResource(R.drawable.splash_logo);
		displayView(menu_selected);
	}

	private void insertDummyContactWrapper() {
		List<String> permissionsNeeded = new ArrayList<>();
		final List<String> permissionsList = new ArrayList<>();

		if (!addPermission(permissionsList, android.Manifest.permission.INTERNET))
			permissionsNeeded.add("INTERNET");
		if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_NETWORK_STATE))
			permissionsNeeded.add("ACCESS_NETWORK_STATE");
		//if (!addPermission(permissionsList, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
			//permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
		//if (!addPermission(permissionsList, android.Manifest.permission.READ_EXTERNAL_STORAGE))
			//permissionsNeeded.add("READ_EXTERNAL_STORAGE");
		//if (!addPermission(permissionsList, android.Manifest.permission.CAMERA))
			//permissionsNeeded.add("CAMERA");
		//if (!addPermission(permissionsList, android.Manifest.permission.FLASHLIGHT))
			////permissionsNeeded.add("FLASHLIGHT");

		if (permissionsList.size() > 0) {
			if (permissionsNeeded.size() > 0) {
				// Need Rationale
				String message = "You need to grant access to " + permissionsNeeded.get(0);
				for (int i = 1; i < permissionsNeeded.size(); i++)
					message = message + ", " + permissionsNeeded.get(i);

				//showMessageOKCancel(message, new DialogInterface.OnClickListener() {
				//@Override
				//public void onClick(DialogInterface dialog, int which) {*/
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
				}
				//}
				//});
				return;
			}
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
			}
			return;
		}
	}

	private boolean addPermission(List<String> permissionsList, String permission) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
				permissionsList.add(permission);
				// Check for Rationale Option
				if (!shouldShowRequestPermissionRationale(permission))
					return false;
			}
		}
		return true;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
			{
				Map<String, Integer> perms = new HashMap<String, Integer>();
				// Initial
				perms.put(android.Manifest.permission.INTERNET, PackageManager.PERMISSION_GRANTED);
				perms.put(android.Manifest.permission.ACCESS_NETWORK_STATE, PackageManager.PERMISSION_GRANTED);
				//perms.put(android.Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
				//perms.put(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
				//perms.put(android.Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
				//perms.put(android.Manifest.permission.FLASHLIGHT, PackageManager.PERMISSION_GRANTED);

				// Fill with results
				for (int i = 0; i < permissions.length; i++)
					perms.put(permissions[i], grantResults[i]);
				// Check for ACCESS_FINE_LOCATION
				if (perms.get(android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
						&& perms.get(android.Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
						//&& perms.get(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
						//&& perms.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
						//&& perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
						//&& perms.get(android.Manifest.permission.FLASHLIGHT) == PackageManager.PERMISSION_GRANTED
					// All Permissions Granted

					menu_selected = 0;
					displayView(menu_selected);
				} else {
					// Permission Denied
					Toast.makeText(context, "Some Permission is Denied", Toast.LENGTH_SHORT).show();
				}
			}
			break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	class prosesUpdateRegisterRegId extends AsyncTask<String, Void, JSONObject> {

		String registrationId;
		boolean success;
		String message;

		prosesUpdateRegisterRegId(String registrationId) {
			this.registrationId = registrationId;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected JSONObject doInBackground(String... urls) {
			return ServerUtilities.register(context, registrationId, data.getId(), CommonUtilities.getGuestId(context));
		}

		@Deprecated
		@Override
		protected void onPostExecute(JSONObject result) {

			success = false;
			message = "Gagal melakukan proses take action. Cobalah lagi.";
			if(result!=null) {
				try {
					success = result.isNull("success")?false:result.getBoolean("success");
					message = result.isNull("message")?message:result.getString("message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!success) {
				new prosesUpdateRegisterRegId(registrationId).execute();
			}
		}
	}

	public Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			mHandlerClose.removeCallbacks(this);
			current_click = 0;
		}
	};

	public Runnable mDisplayViewTask = new Runnable() {
		public void run() {
			mHandlerDisplayView.removeCallbacks(this);
			//displayViewOpen(menu_selected);
		}
	};

	void openSoftKeyboard() {
		View view = getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		}
	}

	void closeSoftKeyboard() {
		View view = getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}