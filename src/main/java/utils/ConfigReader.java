package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ConfigReader {
	private static final String ROOT_CONFIG_PATH = "test-execution-config.yaml";
	private static final String ENV_CONFIG_PATH = "config/environment-config.yaml";
	private static Map<String, Object> testConfigData = Collections.emptyMap();
	private static Map<String, Object> envConfigData = Collections.emptyMap();

	static {
		loadRootConfig();
		loadEnvConfig();
	}

	public static void loadRootConfig() {
		testConfigData = loadConfig(ROOT_CONFIG_PATH);
	}

	public static void loadEnvConfig() {
		envConfigData = loadConfig(ENV_CONFIG_PATH);
	}

	private static Map<String, Object> loadConfig(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new RuntimeException("⚠ YAML file not found: " + filePath);
		}

		try (FileInputStream fis = new FileInputStream(file)) {
			Yaml yaml = new Yaml();
			Map<String, Object> data = yaml.load(fis);
			return (data != null) ? data : Collections.emptyMap();
		} catch (IOException e) {
			throw new RuntimeException("❌ Error loading YAML config file: " + filePath, e);
		}
	}

	public static String getAmazonUrl() {
		String defaultCountry = getNestedProperty("browser", "baseUrl");
		String country = System.getProperty("env", defaultCountry);
		@SuppressWarnings("unchecked")
		Map<String, Object> amazonUrls = (Map<String, Object>) envConfigData.get("amazon");
		String amazonUrl = getUrlForCountry(defaultCountry);
		if (amazonUrls == null) {
			throw new RuntimeException("⚠ Amazon URLs not found in environment-config.yaml!");
		}
		return amazonUrls.getOrDefault(country, amazonUrl).toString();
	}

	private static String getUrlForCountry(String country) {
		Map<String, Object> inputStream = loadConfig(ENV_CONFIG_PATH);
		if (inputStream != null && inputStream.containsKey("amazon")) {
			@SuppressWarnings("unchecked")
			Map<String, String> amazonUrls = (Map<String, String>) inputStream.get("amazon");
			if (amazonUrls != null && amazonUrls.containsKey(country)) {
				return amazonUrls.get(country);
			} else {
				return "URL not found for country: " + country;
			}
		} else {
			return "Amazon data not found in config";
		}
	}

	public static String getNestedProperty(String parentKey, String childKey) {
		@SuppressWarnings("unchecked")
		Map<String, Object> nestedMap = (Map<String, Object>) testConfigData.get(parentKey);
		return (nestedMap != null) ? nestedMap.getOrDefault(childKey, "").toString() : "";
	}
}
