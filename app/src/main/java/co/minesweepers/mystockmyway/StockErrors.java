package co.minesweepers.mystockmyway;

/**
 *  This file contains all the error codes that are possible
 *  It also returns the error message for the error codes
 */
public class StockErrors {

	public static final int JSON_PARSE_ERROR = 0;
	public static final int UNKNOWN_SERVER_ERROR = -1;

	public static String getMessage(int error) {
		String errorMessage;
		switch (error) {
			case JSON_PARSE_ERROR:
				errorMessage = "Internal server error";
				break;
			default:
				errorMessage = "Unknown error";
		}

		return errorMessage;
	}
}
