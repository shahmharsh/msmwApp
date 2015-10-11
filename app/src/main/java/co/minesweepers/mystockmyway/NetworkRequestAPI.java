package co.minesweepers.mystockmyway;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public class NetworkRequestAPI {
    private static NetworkRequestAPI mInstance;

    private OkHttpClient mClient;

    private NetworkRequestAPI() {
        mClient = new OkHttpClient();
    }

    public static synchronized NetworkRequestAPI getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkRequestAPI();
        }

        return mInstance;
    }

    public void get(HttpUrl url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

}
