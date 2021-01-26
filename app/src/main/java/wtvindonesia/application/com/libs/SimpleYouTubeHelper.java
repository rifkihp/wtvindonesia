package wtvindonesia.application.com.libs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wtvindonesia.application.com.utils.Constants;


public class SimpleYouTubeHelper {

    public static String getTitleQuietly(String videoId) {
        String title = null;
        if (videoId != null) {
            String url = "https://www.googleapis.com/youtube/v3/videos?part=id%2C+snippet&id="+videoId+"&key="+ Constants.DEVELOPER_KEY;
            System.out.println(url);
            JSONObject json = new JSONParser().getJSONFromUrl(url, null, null);
            if(json!=null) {
                try {

                    JSONArray topics = json.isNull("items")?null:json.getJSONArray("items");
                    for (int i=0; i<topics.length(); i++) {
                        JSONObject rec = topics.getJSONObject(i);
                        JSONObject snippet  = rec.getJSONObject("snippet");
                        title    = snippet.getString("title");
                    }

                    return title;
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return title;
    }
}