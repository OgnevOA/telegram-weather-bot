package guago.weatherbot.weather;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import guago.weatherbot.weather.dto.WeatherResponceDto;

public class Forecast {
	
	public static WeatherResponceDto getWeatherForecast(double lat, double lon, String apiKey, String lang) {
		RestTemplate restTemplate = new RestTemplate();
		String httpUrl = "https://api.openweathermap.org/data/2.5/onecall";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
				.queryParam("lang", lang)
				.queryParam("units", "metric")
				.queryParam("lang", lang)
				.queryParam("exclude", "hourly,minutely,current,alerts")
				.queryParam("appid", apiKey);
		RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, builder.build().toUri());
		ResponseEntity<WeatherResponceDto> responseEntity = restTemplate.exchange(requestEntity, WeatherResponceDto.class);
		return responseEntity.getBody();
	}
}
