package guago.weatherbot.geocoding;

import java.nio.charset.Charset;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import guago.weatherbot.geocoding.dto.CityDescriptionDto;
import lombok.Getter;

@Getter
public class Geocode {
	private static RestTemplate restTemplate = new RestTemplate();

	public static CityDescriptionDto getLocation(String city, String apiKey) {
		String httpUrl = "http://api.openweathermap.org/geo/1.0/direct";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
				.queryParam("q", city)
				.queryParam("limit", 1)
				.queryParam("appid", apiKey);
		return getResponse(builder).getBody()[0];
	}

	public static CityDescriptionDto getLocation(double lat, double lon, String apiKey) {
		String httpUrl = "http://api.openweathermap.org/geo/1.0/reverse";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
				.queryParam("limit", 1)
				.queryParam("appid", apiKey);
		return getResponse(builder).getBody()[0];
	}

	private static ResponseEntity<CityDescriptionDto[]> getResponse(UriComponentsBuilder builder) {
		builder.encode(Charset.forName("UTF-8"));
		RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, builder.build().toUri());
		ResponseEntity<CityDescriptionDto[]> responseEntity = restTemplate.exchange(requestEntity,
				CityDescriptionDto[].class);
		return responseEntity;
	}

}
