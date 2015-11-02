package co.minesweepers.mystockmyway.manager;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 *
 */
public class NetworkRequestAPI implements INetworkRequestAPI {
    private static INetworkRequestAPI mInstance;

    private OkHttpClient mClient;

    private NetworkRequestAPI() {
        mClient = new OkHttpClient();
    }

    public static synchronized INetworkRequestAPI getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkRequestAPI();
        }

        return mInstance;
    }

    @Override
    public void get(HttpUrl url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

}
