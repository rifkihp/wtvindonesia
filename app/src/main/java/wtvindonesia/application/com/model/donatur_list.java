package wtvindonesia.application.com.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class donatur_list implements Parcelable {

	ArrayList<donatur> listData;
	
	public donatur_list(ArrayList<donatur> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public donatur_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<donatur> getListData() {
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
    public static Creator<donatur_list> CREATOR = new Creator<donatur_list>() {

        @Override
        public donatur_list createFromParcel(Parcel source) {
            return new donatur_list(source);
        }

        @Override
        public donatur_list[] newArray(int size) {
            return new donatur_list[size];
        }

    };

}


