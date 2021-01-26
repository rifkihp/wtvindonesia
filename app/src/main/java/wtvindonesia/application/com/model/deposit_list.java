package wtvindonesia.application.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class deposit_list implements Parcelable {

	ArrayList<deposit> listData;
	
	public deposit_list(ArrayList<deposit> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public deposit_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<deposit> getListData() {
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
    public static Creator<deposit_list> CREATOR = new Creator<deposit_list>() {

        @Override
        public deposit_list createFromParcel(Parcel source) {
            return new deposit_list(source);
        }

        @Override
        public deposit_list[] newArray(int size) {
            return new deposit_list[size];
        }

    };

}


