package wtvindonesia.application.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class YoutubeVideoModel_list implements Parcelable {

	ArrayList<YoutubeVideoModel> listData;
	
	public YoutubeVideoModel_list(ArrayList<YoutubeVideoModel> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public YoutubeVideoModel_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<YoutubeVideoModel> getListData() {
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
    public static Creator<YoutubeVideoModel_list> CREATOR = new Creator<YoutubeVideoModel_list>() {

        @Override
        public YoutubeVideoModel_list createFromParcel(Parcel source) {
            return new YoutubeVideoModel_list(source);
        }

        @Override
        public YoutubeVideoModel_list[] newArray(int size) {
            return new YoutubeVideoModel_list[size];
        }

    };

}


