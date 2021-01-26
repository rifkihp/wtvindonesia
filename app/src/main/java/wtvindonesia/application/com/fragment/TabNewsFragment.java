package wtvindonesia.application.com.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import customfonts.MyTextView;
import wtvindonesia.application.com.wtvindonesia.MainActivity;
import wtvindonesia.application.com.wtvindonesia.R;

public class TabNewsFragment extends Fragment {

    MyTextView culture;
    MyTextView politics;
    MyTextView entertainment;
    MyTextView others;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_tab_news, container, false);

        culture = rootView.findViewById(R.id.txtCulture);
        politics = rootView.findViewById(R.id.txtPolitics);
        entertainment = rootView.findViewById(R.id.txtEntertainment);
        others = rootView.findViewById(R.id.txtOthers);

        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDetailNews(1, "Culture");
            }
        });

        politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDetailNews(2, "Politics");
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDetailNews(3, "Entertainment");
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDetailNews(4, "Others");
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
