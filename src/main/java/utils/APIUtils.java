package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtils {

	public static Response getAPIResponse(String url) {
		return RestAssured.given().relaxedHTTPSValidation() // ✅ Ignores SSL issues
				.get(url);
	}

	public static String getNameFromResponse(Response response, int index) {
		try {
			return response.jsonPath().getString("[" + (index - 1) + "].name");
		} catch (Exception e) {
			throw new RuntimeException("Error extracting name from response: " + e.getMessage(), e);
		}
	}
}
