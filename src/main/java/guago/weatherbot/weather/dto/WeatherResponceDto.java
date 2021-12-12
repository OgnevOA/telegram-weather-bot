package guago.weatherbot.weather.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;

@Getter
public class WeatherResponceDto {
	String timezone;
	double lat;
	double lon;
	@JsonAlias({"timezone_offset"})
	double timezoneOffset;
	List<WeatherConditionDto> daily;

}
