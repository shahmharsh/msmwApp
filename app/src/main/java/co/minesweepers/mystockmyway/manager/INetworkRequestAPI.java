package co.minesweepers.mystockmyway.manager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;

/**
 * All network related request handled here
 */
public interface INetworkRequestAPI {
    void get(HttpUrl url, Callback callback);
}
