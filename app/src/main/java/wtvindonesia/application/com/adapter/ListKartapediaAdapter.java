package wtvindonesia.application.com.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alexzh.circleimageview.CircleImageView;

import java.util.ArrayList;

import customfonts.MyTextView;
import wtvindonesia.application.com.libs.CommonUtilities;
import wtvindonesia.application.com.wtvindonesia.MainActivity;
import wtvindonesia.application.com.wtvindonesia.R;
import wtvindonesia.application.com.model.kategori;

public class ListKartapediaAdapter extends BaseAdapter {

    ArrayList<kategori> listDataKategori;
    public ListKartapediaAdapter(ArrayList<kategori> listDataKategori) {
        this.listDataKategori = listDataKategori;
    }

    public void UpdateListKategoriAdapter(ArrayList<kategori> listDataKategori) {
        this.listDataKategori = listDataKategori;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listDataKategori.size();
    }

    @Override
    public Object getItem(int position) {
        return listDataKategori.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public CircleImageView image;
        public MyTextView title;
        public int position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        final ViewHolder view;
        LayoutInflater inflator =  LayoutInflater.from(parent.getContext());
        if(convertView==null) {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.listkartapedia, null);

            view.image = convertView.findViewById(R.id.image);
            view.title = convertView.findViewById(R.id.title);
            view.image.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        final kategori prod = listDataKategori.get(position);
        view.position = listDataKategori.indexOf(prod);

        String server = CommonUtilities.SERVER_URL;
        String url = server+"/uploads/category/"+listDataKategori.get(position).getHeader();
        MainActivity.imageLoader.displayImage(url, view.image, MainActivity.imageOption);

        view.title.setText(listDataKategori.get(position).getNama());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View convertView) {
                ((MainActivity) parent.getContext()).openDetailCategory(prod);
            }
        });

        return convertView;
    }
}
