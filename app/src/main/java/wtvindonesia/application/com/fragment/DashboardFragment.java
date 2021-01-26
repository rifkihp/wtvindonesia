package wtvindonesia.application.com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import wtvindonesia.application.com.adapter.PagerMenuAdapter;
import wtvindonesia.application.com.wtvindonesia.MainActivity;
import wtvindonesia.application.com.wtvindonesia.R;
import wtvindonesia.application.com.libs.CustomTabLayout;

public class DashboardFragment extends Fragment {

	static CustomTabLayout tabpager;
	static ViewPager pager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

		tabpager  = rootView.findViewById(R.id.tab_layout);
		pager     = rootView.findViewById(R.id.pager);

		pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabpager));
		tabpager.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				final int position = tab.getPosition();
				pager.setCurrentItem(position, true);
				/*if(TabHomeFragment.youTubePlayer!=null && TabHomeFragment.youTubePlayer.isPlaying()) {
					TabHomeFragment.youTubePlayer.pause();
				}*/
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		tabpager.addTab(tabpager.newTab().setText("HOME"));
		tabpager.addTab(tabpager.newTab().setText("TV SHOW"));
		tabpager.addTab(tabpager.newTab().setText("MOVIES"));
		tabpager.addTab(tabpager.newTab().setText("NEWS"));

		PagerMenuAdapter pagerMenuAdapter = new PagerMenuAdapter(((MainActivity) getActivity()).getSupportFragmentManager());
		pager.setAdapter(pagerMenuAdapter);

		return rootView;
	}

	@Override
	public void onStop() {

		super.onStop();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

}
