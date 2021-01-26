package wtvindonesia.application.com.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import wtvindonesia.application.com.libs.EndlessRecyclerViewScrollListener;
import wtvindonesia.application.com.utils.RecyclerViewOnClickListener;
import wtvindonesia.application.com.wtvindonesia.MainActivity;
import wtvindonesia.application.com.wtvindonesia.R;

public class TabMoviesFragment extends Fragment {

    static SwipeRefreshLayout swipeRefresh;
    public static RecyclerView recyclerView;

    public static ProgressBar loading;
    public static LinearLayout retry;
    static Button btnReload;

    public static EndlessRecyclerViewScrollListener scrollListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_tab_movies, container, false);

        swipeRefresh = rootView.findViewById(R.id.swipe_container);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        loading      = rootView.findViewById(R.id.pgbarLoading);
        retry        = rootView.findViewById(R.id.loadMask);
        btnReload    = rootView.findViewById(R.id.btnReload);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                ((MainActivity) getActivity()).loadDataMovies(false);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        //set click event
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(getActivity().getApplicationContext(), new RecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ((MainActivity) getActivity()).openDetailVideo(MainActivity.movieslist.get(position).getVideoId());

                //start youtube player activity by passing selected video id via intent
                //startActivity(new Intent(YouTubeMainActivity.this, YouTubePlayerActivity.class)
                //.putExtra("video_id", youtubeVideoModelArrayList.get(position).getVideoId()));

            }
        }));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                ((MainActivity) getActivity()).loadDataMovies(true);
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ((MainActivity) getActivity()).loadDataMovies(false);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).loadDataMovies(true);
    }
}
