package wtvindonesia.application.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class news_list implements Parcelable {

	ArrayList<news> listData;
	
	public news_list(ArrayList<news> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public news_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<news> getListData() {
		return this.listData;
	}
	
	@Override
    public int describeContents() {
        return 0;
    }

    // Required method to write to Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listData);
    }

    // Method to recreate a Question from a Parcel
    public static Creator<news_list> CREATOR = new Creator<news_list>() {

        @Override
        public news_list createFromParcel(Parcel source) {
            return new news_list(source);
        }

        @Override
        public news_list[] newArray(int size) {
            return new news_list[size];
        }

    };

}


