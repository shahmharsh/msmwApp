package co.minesweepers.mystockmyway.manager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;

/**
 * All network related request handled here
 */
public interface INetworkRequestAPI {

	/**
	 * Makes a get call to the server
	 *
	 * @param url
	 *          {@code HttpUrl}
	 * @param callback
	 *          {@code Callback}
	 */
    void get(HttpUrl url, Callback callback);
}
