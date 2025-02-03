package stepdefinitions.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.APIUtils;

public class MobileAPISteps {
	private Response response;

	@Given("I have a mobile API")
	public void iHaveAMobileAPI() {
		response = APIUtils.getAPIResponse("https://api.restful-api.dev/objects");
		assertNotNull(response, "Response should not be null");
	}

	@When("I validate the response")
	public void iValidateTheResponse() {
		assertNotNull(response, "Response validation failed, response is null");
	}

	@Then("the status code should be {int}")
	public void theStatusCodeShouldBe(int expectedStatusCode) {
		assertEquals(expectedStatusCode, response.getStatusCode(), "Status code mismatch");
	}

	@Then("the name of the {int}th id should be {string}")
	public void theNameOfTheNthIdShouldBe(int index, String expectedName) {
		try {
			String jsonResponse = response.getBody().asString();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonData = objectMapper.readTree(jsonResponse);

			String actualName = jsonData.get(index - 1).get("name").asText();
			System.out.println("Product Name at ID " + index + ": " + actualName);

			assertEquals(expectedName.replaceAll("\\s+", " ").trim(), actualName.replaceAll("\\s+", " ").trim(),
					"Product name mismatch!");
		} catch (Exception e) {
			fail("Error parsing JSON response: " + e.getMessage());
		}
	}
}
