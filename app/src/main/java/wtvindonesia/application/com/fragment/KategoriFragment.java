package wtvindonesia.application.com.fragment;

import android.app.Fragment;
import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import customfonts.MyTextView;
import wtvindonesia.application.com.libs.EndlessRecyclerViewScrollListener;
import wtvindonesia.application.com.utils.RecyclerViewOnClickListener;
import wtvindonesia.application.com.wtvindonesia.MainActivity;
import wtvindonesia.application.com.wtvindonesia.R;

public class KategoriFragment extends Fragment {

	public static MyTextView title;
	public static MyTextView textView2;

	static ImageView back;
	static SwipeRefreshLayout swipeRefresh;
	public static RecyclerView recyclerView;

	public static ProgressBar loading;
	public static LinearLayout retry;
	static Button btnReload;

	public static EndlessRecyclerViewScrollListener scrollListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_kategori, container, false);

		title = rootView.findViewById(R.id.title);
		textView2 = rootView.findViewById(R.id.textView2);
		back = rootView.findViewById(R.id.back);
		swipeRefresh = rootView.findViewById(R.id.swipe_container);
		recyclerView = rootView.findViewById(R.id.recycler_view);

		loading       = rootView.findViewById(R.id.pgbarLoading);
		retry         = rootView.findViewById(R.id.loadMask);
		btnReload     = rootView.findViewById(R.id.btnReload);

		recyclerView.setHasFixedSize(true);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		recyclerView.setLayoutManager(linearLayoutManager);

		scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
			@Override
			public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
				// Triggered only when new data needs to be appended to the list
				// Add whatever code is needed to append new items to the bottom of the list
				((MainActivity) getActivity()).loadDataVideoKetegori(false);
			}
		};
		// Adds the scroll listener to RecyclerView
		recyclerView.addOnScrollListener(scrollListener);

		//set click event
		recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(getActivity().getApplicationContext(), new RecyclerViewOnClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				((MainActivity) getActivity()).openDetailVideoKategori(position);
			}
		}));
		swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				swipeRefresh.setRefreshing(false);
				((MainActivity) getActivity()).loadDataVideoKetegori(true);
			}
		});

		btnReload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).loadDataVideoKetegori(false);
			}
		});

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).displayView(0);
			}
		});

		return rootView;
	}

	@Override
	public void onStop() {

		super.onStop();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		((MainActivity) getActivity()).loadDataVideoKetegori(true);
	}

}
