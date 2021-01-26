package wtvindonesia.application.com.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class donasi_list implements Parcelable {

	ArrayList<donasi> listData;
	
	public donasi_list(ArrayList<donasi> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public donasi_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<donasi> getListData() {
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
    public static Creator<donasi_list> CREATOR = new Creator<donasi_list>() {

        @Override
        public donasi_list createFromParcel(Parcel source) {
            return new donasi_list(source);
        }

        @Override
        public donasi_list[] newArray(int size) {
            return new donasi_list[size];
        }

    };

}


