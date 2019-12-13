package com.xolisa.creditcardrestapi;

import com.xolisa.creditcardrestapi.entity.Country;
import com.xolisa.creditcardrestapi.repository.CountryRepository;
import com.xolisa.creditcardrestapi.repository.CreditCardRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public void run(String... args) throws Exception {
		countryRepository.deleteAll();
		creditCardRepository.deleteAll();

		/** Configuring Banned Countries - Loading countries and randomly banning some of them**/

		String countries = getContries();
		JSONObject obj = new JSONObject(countries);
		Map countriesMap = obj.toMap();
		ArrayList<String> keyList = new ArrayList<String>(countriesMap.keySet());
		ArrayList<String> valueList = new ArrayList<String>(countriesMap.values());
		for (int i = 0; i <  10; i++) {
			int random = new Random().nextInt(50) + 1;
			if (random % 2 == 0) {
				countryRepository.save(new Country(keyList.get(i), valueList.get(i), true, ""));
			} else {
				countryRepository.save(new Country(keyList.get(i), valueList.get(i), false, ""));
			}
		}
		List<Country> allCountries = countryRepository.findAll();
		allCountries.stream().forEach(c -> System.out.println(c.getIso2()+" - "+c.getName()+" - "+ c.isBanned()) );
	}

	public static String getContries() throws IOException {
		URL urlForGetRequest = new URL("http://country.io/names.json");
		String readLine = null;

		HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
		connection.setRequestMethod("GET");

		if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Something went wrong. HttpResponseCode : "+connection.getResponseCode());
		} else {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();

			while ((readLine = in .readLine()) != null) {
				response.append(readLine);
			}
			in .close();
			return response.toString();
		}
	}
}
