package wtvindonesia.application.com.wtvindonesia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import customfonts.MyTextView;
import wtvindonesia.application.com.adapter.YoutubeVideoAdapter;
import wtvindonesia.application.com.libs.CommonUtilities;
import wtvindonesia.application.com.libs.EndlessRecyclerViewScrollListener;
import wtvindonesia.application.com.libs.JSONParser;
import wtvindonesia.application.com.model.YoutubeVideoModel;
import wtvindonesia.application.com.model.YoutubeVideoModel_list;
import wtvindonesia.application.com.model.kategori;
import wtvindonesia.application.com.utils.RecyclerViewOnClickListener;

public class YouTubeCategoryActivity extends AppCompatActivity {

    Context context;
    MyTextView title;
    MyTextView textView2;

    ImageView back;
    SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;

    ProgressBar loading;
    LinearLayout retry;
    Button btnReload;

    int page;
    ArrayList<YoutubeVideoModel> datalist = new ArrayList<>();
    public static YoutubeVideoAdapter dataAdapter;
    
    EndlessRecyclerViewScrollListener scrollListener;
    
    kategori kategorivid;
    boolean is_news;
    boolean is_banner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_category);

        context = YouTubeCategoryActivity.this;
        title = findViewById(R.id.title);
        textView2 = findViewById(R.id.textView2);
        back = findViewById(R.id.back);
        swipeRefresh = findViewById(R.id.swipe_container);
        recyclerView = findViewById(R.id.recycler_view);

        loading       = findViewById(R.id.pgbarLoading);
        retry         = findViewById(R.id.loadMask);
        btnReload     = findViewById(R.id.btnReload);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadDataVideos(false);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        //set click event
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(getApplicationContext(), new RecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                openDetailVideo(datalist.get(position).getVideoId());
            }
        }));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                loadDataVideos(true);
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                loadDataVideos(false);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        
        if(savedInstanceState==null) {
            kategorivid = (kategori) getIntent().getSerializableExtra("category");
            is_news     = getIntent().getBooleanExtra("is_news", false);
            is_banner   = getIntent().getBooleanExtra("is_banner", false);
            title.setText(kategorivid.getNama().toUpperCase());
        }
        
        loadDataVideos(true);
    }
    
    public void loadDataVideos(boolean first) {
        if(first) {
            page = 1;
            datalist.clear();
            dataAdapter = new YoutubeVideoAdapter(context, datalist);
            recyclerView.setAdapter(dataAdapter);
            scrollListener.resetState();
        }
        new prosesLoadDataVideos().execute();
    }

    public class prosesLoadDataVideos extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            textView2.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            retry.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(String... urls) {

            ArrayList<YoutubeVideoModel> result = null;
            String url = CommonUtilities.SERVER_URL + "/store/androidVideo"+(is_banner?"Banner":(is_news?"News":"Category"))+"Store.php";

            Log.e("BOBABA", url + "   " + kategorivid.getId() );
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("page", page+""));
            params.add(new BasicNameValuePair("category", kategorivid.getId()+""));

            JSONObject json = new JSONParser().getJSONFromUrl(url, params, null);
            if(json!=null) {
                try {
                    result = new ArrayList<>();
                    page = json.isNull("next_page") ? page : json.getInt("next_page");
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

            Intent i = new Intent("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_VIDEOS_CATEGORY");
            i.putExtra("data_videos_list", temp);
            i.putExtra("success", success);
            sendBroadcast(i);

            return null;
        }
    }

    void openDetailVideo(String  videoId) {
        Intent i = new Intent(context, YouTubePlayerActivity.class);
        i.putExtra("video_id", videoId);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(mHandleLoadDataVideosReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        try {
            unregisterReceiver(mHandleLoadDataVideosReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPause();
    }

    @Override
    protected void onResume() {
        registerReceiver(mHandleLoadDataVideosReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_DATA_VIDEOS_CATEGORY"));
        super.onResume();
    }

    private final BroadcastReceiver mHandleLoadDataVideosReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Boolean success = intent.getBooleanExtra("success", false);
            loading.setVisibility(View.GONE);
            if(success) {
                ArrayList<YoutubeVideoModel_list> temp = intent.getParcelableArrayListExtra("data_videos_list");
                ArrayList<YoutubeVideoModel> result = temp.get(0).getListData();
                if(result.size()>0) {
                    for (YoutubeVideoModel flist : result) {
                        datalist.add(flist);
                    }
                    dataAdapter.updateAdapter(datalist);
                }

                if(datalist.size()==0) {
                    textView2.setVisibility(View.VISIBLE);
                }
            } else {
                retry.setVisibility(View.VISIBLE);
            }
        }
    };
}