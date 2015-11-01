package co.minesweepers.mystockmyway;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;

/**
 * Created by Horsie on 10/12/15.
 *
 */
public interface INetworkRequestAPI {
    void get(HttpUrl url, Callback callback);
}
