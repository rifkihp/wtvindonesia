package wtvindonesia.application.com.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class beli_list implements Parcelable {

	ArrayList<beli> listData;
	
	public beli_list(ArrayList<beli> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public beli_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<beli> getListData() {
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
    public static Creator<beli_list> CREATOR = new Creator<beli_list>() {

        @Override
        public beli_list createFromParcel(Parcel source) {
            return new beli_list(source);
        }

        @Override
        public beli_list[] newArray(int size) {
            return new beli_list[size];
        }

    };

}


