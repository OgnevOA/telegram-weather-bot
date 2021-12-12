package guago.weatherbot.geocoding.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;

@Getter
public class CityDescriptionDto {
	String name;
	@JsonAlias({"local_names"})
	Map<String, String> localNames;
	double lat;
	double lon;
	String country;
	String state;

}
