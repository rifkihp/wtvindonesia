package wtvindonesia.application.com.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import wtvindonesia.application.com.fragment.TabHomeFragment;
import wtvindonesia.application.com.fragment.TabMoviesFragment;
import wtvindonesia.application.com.fragment.TabNewsFragment;
import wtvindonesia.application.com.fragment.TabTvShowFragment;

public class PagerMenuAdapter extends FragmentStatePagerAdapter {

    public PagerMenuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabHomeFragment tab_home = new TabHomeFragment();

                return tab_home;

            case 1:
                TabTvShowFragment tab_tv_show = new TabTvShowFragment();

                return tab_tv_show;

            case 2:
                TabMoviesFragment tab_movies = new TabMoviesFragment();

                return tab_movies;

            case 3:
                TabNewsFragment tab_news = new TabNewsFragment();

                return tab_news;

            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return 4;
    }
}