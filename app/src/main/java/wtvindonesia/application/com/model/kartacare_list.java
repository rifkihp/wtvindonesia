package wtvindonesia.application.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class kartacare_list implements Parcelable {

	ArrayList<kartacare> listData;
	
	public kartacare_list(ArrayList<kartacare> listData) {
		this.listData = listData;
	}
	
	@SuppressWarnings("unchecked")
	public kartacare_list(Parcel parcel) {
		this.listData = parcel.readArrayList(null);
	}
	
	public ArrayList<kartacare> getListData() {
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
    public static Creator<kartacare_list> CREATOR = new Creator<kartacare_list>() {

        @Override
        public kartacare_list createFromParcel(Parcel source) {
            return new kartacare_list(source);
        }

        @Override
        public kartacare_list[] newArray(int size) {
            return new kartacare_list[size];
        }

    };

}


